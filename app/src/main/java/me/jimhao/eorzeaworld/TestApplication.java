package me.jimhao.eorzeaworld;

import android.app.Application;

import me.jimhao.eorzeautil.log.CrashHandlerUtil;

/**
 * 作者： guhaoran
 * 创建于： 2017/7/26
 * 包名： me.jimhao.eorzeaworld
 * 文档描述：e
 */
public class TestApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        CrashHandlerUtil util = CrashHandlerUtil.getInstance();
        util.init(getApplicationContext());
    }
}
