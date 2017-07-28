package me.jimhao.eorzeautil.image;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.PixelFormat;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

import java.io.ByteArrayOutputStream;
import java.io.File;

import me.jimhao.eorzeautil.log.EasyLog;

/**
 * 作者： guhaoran
 * 创建于： 2017/7/28
 * 包名： me.jimhao.eorzeautil.image
 * 文档描述：简单图像处理类
 */
public class EasyBitmap {
    /**
     * [将Bitmap转换为Drawable]
     * @param bitmap
     * */
    public static Drawable getBitmapToDrawable(Bitmap bitmap){
        BitmapDrawable bitmapDrawable = new BitmapDrawable(bitmap);
        return bitmapDrawable;
    }
    /**
     * [从资源文件ID得到Drawable]
     *
     * @param context
     * @param resId
     * @return
     */
    public static Drawable getDrawableById(Context context, int resId) {
        if (context == null) {
            return null;
        }
        return context.getResources().getDrawable(resId);
    }

    /**
     * [从资源文件ID得到Bitmap]
     *
     * @param context
     * @param resId
     * @return
     */
    public static Bitmap getBitmapById(Context context, int resId) {
        if (context == null) {
            return null;
        }
        return BitmapFactory.decodeResource(context.getResources(), resId);
    }

    /**
     * [从文件地址中得到Bitmap]
     *
     * @param filePath
     * @return
     */
    public static Bitmap getBitmapByFile(String filePath) {
        return getBitmapByFile(filePath, null);
    }
    /**
     * [从文件中得到Bitmap]
     *
     * @param file
     * @return
     */
    public static Bitmap getBitmapByFile(File file){
        String path = file.getPath() ;
        EasyLog.i("Path",path);
        return getBitmapByFile(path,null);
    }
    /**
     * [从文件地址中得到Bitmap,可配置设置]
     *
     * @param filePath
     * @param opts
     * @return
     */
    public static Bitmap getBitmapByFile(String filePath,BitmapFactory.Options opts) {
        if (filePath == null) {
            return null;
        }
        return BitmapFactory.decodeFile(filePath,opts);
    }
    /**
     * [从Drawable中得到Bitmap]
     * @param drawable
     * @return
     */
    public static Bitmap getBitmapByDrawable(Drawable drawable) {
        if (drawable == null) {
            return null;
        }
        //构建一个Bitmap
        Bitmap bitmap = Bitmap.createBitmap(
                drawable.getIntrinsicWidth(),	//从drawable中得到实际长度
                drawable.getIntrinsicHeight(),	//从drawable中得到实际宽度
                drawable.getOpacity() != PixelFormat.OPAQUE ? Bitmap.Config.ARGB_8888: Bitmap.Config.RGB_565);
        Canvas canvas = new Canvas(bitmap);
        // canvas.setBitmap(bitmap);
        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(),
                drawable.getIntrinsicHeight());
        drawable.draw(canvas);	//绘制

        return bitmap;
    }
    /**
     * [将Bitmap转换成字节数组]
     * @param bitmap
     * @return
     */
    public static byte[] bitmapToByteArray(Bitmap bitmap){
        ByteArrayOutputStream baos = null ;
        try {
            baos = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
            byte[] array = baos.toByteArray();
            baos.flush();
            baos.close();
            return array ;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null ;
    }
    /**
     * [将字节数组转换成Bitmap]
     * @param data
     * @return
     */
    public static Bitmap byteArrayToBitmap(byte[] data){
        if(null == data){
            return null ;
        }
        return BitmapFactory.decodeByteArray(data, 0, data.length);
    }


}
