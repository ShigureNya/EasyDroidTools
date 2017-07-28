package me.jimhao.eorzeautil.media;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;

/**
 * 作者： guhaoran
 * 创建于： 2017/7/28
 * 包名： me.jimhao.eorzeautil.media
 * 文档描述：相机工具类
 */
public class CameraUtil {
    /**
     * 进入系统拍照并保存
     * @param activity activity
     * @param saveUri 图片保存的路径
     */
    public static void startActivityForCamera(Activity activity, int requestCode, Uri saveUri) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.addCategory(Intent.CATEGORY_DEFAULT);
        // 制定图片保存路径
        intent.putExtra(MediaStore.EXTRA_OUTPUT, saveUri);
        activity.startActivityForResult(intent, requestCode);
    }

    /*
    下面两个方法需要在ActivityForResult中去接收返回值，
    通过Uri uri = intent.getData()获取
    得到的结果路径为content://media/external/images/media/32073
    需要用EasyFile中的getUrlToLocalPath取出绝对路径
    */
    /**
     * [打开系统照相机]
     * 进入系统拍照 (输出为Bitmap)
     * @param activity activity
     * @param requestCode ActivityForResult的请求和返回值
     */
    public static void startActivityForCamera(Activity activity, int requestCode) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.addCategory(Intent.CATEGORY_DEFAULT);
        activity.startActivityForResult(intent, requestCode);
    }

    /**
     * [进入系统图库]
     * @param activity 当前activity
     * @param requestCode ActivityForResult的请求和返回值
     */
    public static void startActivityForGallery(Activity activity, int requestCode) {
        // 弹出系统图库
        Intent i = new Intent(Intent.ACTION_PICK,
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        activity.startActivityForResult(i, requestCode);
    }
}
