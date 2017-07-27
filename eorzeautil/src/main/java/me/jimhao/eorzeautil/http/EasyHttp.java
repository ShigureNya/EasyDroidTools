package me.jimhao.eorzeautil.http;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import me.jimhao.eorzeautil.log.NekoLog;
import me.jimhao.eorzeautil.storage.MD5Util;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import static android.content.ContentValues.TAG;

/**
 * 作者： guhaoran
 * 创建于： 2017/7/27
 * 包名： me.jimhao.eorzeautil.http
 * 文档描述：轻松访问Http工具类
 * 【该类功能：
 *  提供统一访问接口
 *  提供异步get和post方法
 *  提供download方法 可选是否转换成MD5
 *  提供upLoad方法
 * 】
 */
public class EasyHttp extends Http {
    private static int readOutTime = 30 ;   //读取超时
    private static int connectOutTime = 15 ; //连接超时时间
    private static int writeOutTime = 60 ;  //写入超时

    private static EasyHttp instance ;

    private static OkHttpClient client ;
    private static OkHttpClient.Builder clientBuilder ;

    /**
     * 初始化HttpClient
     */
    private EasyHttp() {
        super();
        if(client == null){
            client = new OkHttpClient();
        }
        clientBuilder = new OkHttpClient().newBuilder();
        clientBuilder.readTimeout(readOutTime, TimeUnit.SECONDS);    //读取超时30秒
        clientBuilder.connectTimeout(connectOutTime, TimeUnit.SECONDS);
        clientBuilder.writeTimeout(writeOutTime, TimeUnit.SECONDS);
        client = clientBuilder.build();
    }

    /**
     * 单例接口 提供统一访问对象
     * @return 实例对象
     */
    public static EasyHttp getInstance(){
        EasyHttp mInstance = instance;
        if (mInstance == null) {
            synchronized (EasyHttp.class) {
                mInstance = instance;
                if (mInstance == null) {
                    mInstance = new EasyHttp();
                    instance = mInstance;
                }
            }
        }
        return mInstance;
    }


    /**
     * [Get方法]
     * @param url 访问接口
     * @param params 用HashMap作为参数
     * @param callBack 接口返回
     */
    @Override
    public void doGet(String url, Map<String, String> params, final HttpCallBack callBack) {
        if(!params.isEmpty()){
            StringBuilder buffer = new StringBuilder(url);
            buffer.append("?");
            Iterator iter = params.entrySet().iterator();
            while(iter.hasNext()){
                Map.Entry entry = (Map.Entry) iter.next();
                String key = (String) entry.getKey();
                String val = (String) entry.getValue();
                buffer.append(key);
                buffer.append("=");
                buffer.append(val);
                buffer.append("&");
            }
            url = buffer.substring(0,buffer.length()-1);
            NekoLog.d(url);
        }
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                NekoLog.e(TAG,e.getMessage());
                callBack.onFailed(new Throwable(e));
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String result = response.body().string();
                NekoLog.i(TAG,result);
                callBack.onSuccess(result);
            }
        });
    }

    /**
     * [Post方法]
     * @param url 访问接口
     * @param requestBody 请求参数
     * @param callBack 接口返回
     */
    @Override
    public void doPost(String url, Map<String, String> requestBody, final HttpCallBack callBack) {
        OkHttpClient okHttpClient = new OkHttpClient();
        Iterator iter = requestBody.entrySet().iterator();

        FormBody.Builder builder = new FormBody.Builder();
        while(iter.hasNext()){
            Map.Entry entry = (Map.Entry) iter.next();
            String key = (String) entry.getKey();
            String val = (String) entry.getValue();
            builder.add(key,val);
        }
        RequestBody body = builder.build();

        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();

        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                NekoLog.e(TAG,e.getMessage());
                callBack.onFailed(new Throwable(e));
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String result = response.body().string();
                NekoLog.i(TAG,result);
                callBack.onSuccess(result);
            }
        });
    }

    /**
     * [下载方法-异步]
     * @param url 服务器接口
     * @param filePath 下载到本地的地址
     * @param fileName 文件名
     * @param toMD5 是否转换成MD5
     * @param callBack 结果返回接口
     */
    @Override
    public void download(String url, final String filePath, final String fileName, final boolean toMD5 , final DownloadCallBack callBack) {
        Request request = new Request.Builder().url(url).build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                // 下载失败
                callBack.onDownloadFailed();
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                InputStream is = null;
                byte[] buf = new byte[2048];
                int len = 0;
                FileOutputStream fos = null;
                // 储存下载文件的目录
                try {
                    is = response.body().byteStream();
                    long total = response.body().contentLength();
                    //两参 第一个参数是路径 第二个参数是文件名
                    File file ;
                    if(toMD5){
                        file = new File(filePath, MD5Util.getMD5(fileName));
                    }else{
                        file = new File(filePath,fileName);
                    }
                    if(!file.exists()){
                        file.createNewFile();
                    }
                    fos = new FileOutputStream(file);
                    long sum = 0;
                    while ((len = is.read(buf)) != -1) {
                        fos.write(buf, 0, len);
                        sum += len;
                        int progress = (int) (sum * 1.0f / total * 100);
                        // 下载中
                        callBack.onDownloading(progress);
                    }
                    fos.flush();
                    // 下载完成
                    callBack.onDownloadSuccess(file.getAbsolutePath());
                } catch (Exception e) {
                    callBack.onDownloadFailed();
                } finally {
                    try {
                        if (is != null)
                            is.close();
                    } catch (IOException e) {
                    }
                    try {
                        if (fos != null)
                            fos.close();
                    } catch (IOException e) {
                    }
                }
            }
        });
    }

    /**
     * [上传方法-异步]
     * @param url 服务器接口地址
     * @param filePath 文件地址
     * @param callBack 结果返回接口
     */
    @Override
    public void upload(String url, String filePath, final UploadCallBack callBack) {
        //创建File
        File localFile = new File(filePath);
        //创建RequestBody
        RequestBody body = RequestBody.create(MediaType.parse("MEDIA_OBJECT_STREAM"), localFile);
        //创建Request
        final Request request = new Request.Builder().url(url).post(body).build();
        final Call call = client.newBuilder().writeTimeout(50, TimeUnit.SECONDS).build().newCall(request);

        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                NekoLog.i(TAG,"与服务器交互失败");
                callBack.onError(new Throwable(e));
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    String string = response.body().string();
                    NekoLog.e(TAG, "上传成功：" + string);
                    callBack.onUPLoadSuccess(string);
                } else {
                    NekoLog.e(TAG,"上传失败");
                    callBack.onUploadFailed();
                }
            }
        });
    }

    /**[设置读取超时时间]
     * @param readOutTime 读取超时时间
     */
    public static void setReadOutTime(int readOutTime) {
        clientBuilder.readTimeout(readOutTime,TimeUnit.SECONDS);
        client = clientBuilder.build();
    }
    /**[设置连接超时时间]
     * @param connectOutTime 读取连接时间
     */
    public static void setConnectOutTime(int connectOutTime) {
        clientBuilder.connectTimeout(connectOutTime,TimeUnit.SECONDS);
        client = clientBuilder.build();
    }

    /**[设置写入超时时间]
     * @param writeOutTime 读取超时时间
     */
    public static void setWriteOutTime(int writeOutTime) {
        clientBuilder.writeTimeout(writeOutTime,TimeUnit.SECONDS);
        client = clientBuilder.build();
    }

    /**
     * 关闭OkHttp请求
     */
    public void disconnectOkHttp(){
        Object tag = this;
        for (Call call : client.dispatcher().queuedCalls())
        {
            if (tag.equals(call.request().tag()))
            {
                call.cancel();
            }
        }
        for (Call call : client.dispatcher().runningCalls())
        {
            if (tag.equals(call.request().tag()))
            {
                call.cancel();
            }
        }
        NekoLog.w("接口关闭成功");
    }

}
