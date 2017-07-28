package me.jimhao.eorzeautil.view;

import android.app.Application;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import me.jimhao.eorzeautil.log.CrashHandlerUtil;

/**
 * 作者： guhaoran
 * 创建于： 2017/7/26
 * 包名： me.jimhao.eorzeautil.application
 * 文档描述：基础Application类
 */
public abstract class EasyApplication extends Application {
    public static boolean isDebug = true ;  //默认为true
    public static String appName = null ;
    private Context context ;
    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        isDebug = getIsDebug();   //查看是否为debug开发模式
        appName = getAppName(); //获得App名称

        registerCrashHandler(context);
        onBusiness(context);   //完成业务操作
    }

    private boolean getIsDebug(){
        ApplicationInfo info= getApplicationContext().getApplicationInfo();
        return (info.flags& ApplicationInfo.FLAG_DEBUGGABLE)!=0;
    }

    private String getAppName(){
        try {
            PackageManager manager = getApplicationContext().getPackageManager();
            PackageInfo packageInfo = manager.getPackageInfo(
                    getApplicationContext().getPackageName(), 0);
            return packageInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return null ;
    }

    public abstract void onBusiness(Context context);

    public void registerCrashHandler(Context context){
        CrashHandlerUtil util = CrashHandlerUtil.getInstance();
        util.init(context);
    }
}
