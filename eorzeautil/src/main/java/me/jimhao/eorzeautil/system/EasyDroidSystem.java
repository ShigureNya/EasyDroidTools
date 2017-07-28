package me.jimhao.eorzeautil.system;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import java.io.File;

/**
 * 作者： guhaoran
 * 创建于： 2017/7/28
 * 包名： me.jimhao.eorzeautil.system
 * 文档描述：系统工具箱
 */
public class EasyDroidSystem {
    /**
     * [安装apk]
     * @param context 上下文
     * @param path    文件路径
     */
    public static void apkInstall(Context context, String path) {
        Intent intent = new Intent();
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setAction(android.content.Intent.ACTION_VIEW);
        intent.setDataAndType(Uri.fromFile(new File(path)), "application/vnd.android.package-archive");
        context.startActivity(intent);
    }

    /**
     * [拨号]
     *【需要增加CALL_PHONE权限】
     * @param context 上下文
     * @param phone   手机号码
     */
    public static void callPhone(Context context, String phone) {
        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + phone));
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }
}
