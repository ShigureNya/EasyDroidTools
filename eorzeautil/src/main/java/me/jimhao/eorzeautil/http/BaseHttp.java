package me.jimhao.eorzeautil.http;

import java.util.Map;

/**
 * 作者： guhaoran
 * 创建于： 2017/7/27
 * 包名： me.jimhao.eorzeautil.http
 * 文档描述: Http请求类
 */
public abstract class BaseHttp {
    public abstract void doGet(String url , Map<String,String> params , HttpCallBack callBack);

    public abstract void doPost(String url , Map<String,String> params , HttpCallBack callBack);

    public abstract void download(String url , String filePath , String fileName ,boolean toMD5 , DownloadCallBack callBack);

    public abstract void upload(String url , String filePath , UploadCallBack callBack);
}
