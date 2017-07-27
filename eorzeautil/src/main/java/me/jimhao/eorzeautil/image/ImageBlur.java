package me.jimhao.eorzeautil.image;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Build;
import android.renderscript.Allocation;
import android.renderscript.Element;
import android.renderscript.RenderScript;
import android.renderscript.ScriptIntrinsicBlur;
import android.support.annotation.RequiresApi;

import me.jimhao.eorzeautil.log.NekoLog;

/**
 * 作者： guhaoran
 * 创建于： 2017/7/27
 * 包名： me.jimhao.eorzeautil.image
 * 文档描述：图像动态模糊
 */
public class ImageBlur {
    /**
     * 图片缩放比例
     */
    private static final float BITMAP_SCALE = 0.4f;
    /**
     * 最大模糊度(在0.0到25.0之间)
     */
    private static final float BLUR_RADIUS = 25f;

    /**
     * 模糊图片的具体方法
     *
     * @param context   上下文对象
     * @param image     需要模糊的图片
     * @return          模糊处理后的图片
     */
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    public static Bitmap blur(Context context, Bitmap image , float radius) {
        long startTime = System.currentTimeMillis();
        // 计算图片缩小后的长宽
        int width = Math.round(image.getWidth() * BITMAP_SCALE);
        int height = Math.round(image.getHeight() * BITMAP_SCALE);

        // 将缩小后的图片做为预渲染的图片。
        Bitmap inputBitmap = Bitmap.createScaledBitmap(image, width, height, false);
        // 创建一张渲染后的输出图片。
        Bitmap outputBitmap = Bitmap.createBitmap(inputBitmap);

        // 创建RenderScript内核对象
        RenderScript rs = RenderScript.create(context);
        // 创建一个模糊效果的RenderScript的工具对象
        ScriptIntrinsicBlur blurScript = ScriptIntrinsicBlur.create(rs, Element.U8_4(rs));

        // 由于RenderScript并没有使用VM来分配内存,所以需要使用Allocation类来创建和分配内存空间。
        // 创建Allocation对象的时候其实内存是空的,需要使用copyTo()将数据填充进去。
        Allocation tmpIn = Allocation.createFromBitmap(rs, inputBitmap);
        Allocation tmpOut = Allocation.createFromBitmap(rs, outputBitmap);
        if(radius >= BLUR_RADIUS){
            radius = 25 ;
            NekoLog.w("已达到最大模糊程度");
        }
        // 设置渲染的模糊程度, 25f是最大模糊度
        blurScript.setRadius(radius);
        // 设置blurScript对象的输入内存
        blurScript.setInput(tmpIn);
        // 将输出数据保存到输出内存中
        blurScript.forEach(tmpOut);

        // 将数据填充到Allocation中
        tmpOut.copyTo(outputBitmap);
        // 关闭RenderScript对象，API>=23则使用rs.releaseAllContexts()
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            RenderScript.releaseAllContexts();
        }else{
            rs.destroy();
        }
        long stopTime = System.currentTimeMillis();
        NekoLog.e("动态模糊耗时",(stopTime-startTime)+"毫秒");
        return outputBitmap;
    }
}
