package me.jimhao.eorzeautil.log;

import android.util.Log;

/**
 * 作者： guhaoran
 * 创建于： 2017/7/26
 * 包名： me.jimhao.eorzeautil.log
 * 文档描述：猫日志
 */
public class NekoLog {
    public static final String module = "(。・`ω´・)";
    public static void i(String msg){
        Log.i(
                module+"[TAG:Info]",
                "[MSG:]"+msg
        );
    }

    public static void i(String tag , String msg){
        Log.i(
                module+"[TAG:"+tag+"]",
                "[MSG:]"+msg
        );
    }

    public static void e(String msg){
        Log.e(
                module+"[TAG:Error]",
                "[MSG:]"+msg
        );
    }

    public static void e(String tag , String msg){
        Log.e(
                module+"[TAG:"+tag+"]",
                "[MSG:]"+msg
        );
    }

    public static void d(String msg){
        Log.d(
                module+"[TAG:Debug]",
                "[MSG:]"+msg
        );
    }

    public static void d(String tag , String msg){
        Log.d(
                module+"[TAG:"+tag+"]",
                "[MSG:]"+msg
        );
    }


    public static void w(String msg){
        Log.w(
                module+"[TAG:Warning]",
                "[MSG:]"+msg
        );
    }

    public static void w(String tag , String msg){
        Log.w(
                module+"[TAG:"+tag+"]",
                "[MSG:]"+msg
        );
    }
}
