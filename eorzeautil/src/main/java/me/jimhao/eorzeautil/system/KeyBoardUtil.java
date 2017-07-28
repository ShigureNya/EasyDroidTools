package me.jimhao.eorzeautil.system;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

/**
 * 作者： guhaoran
 * 创建于： 2017/7/28
 * 包名： me.jimhao.eorzeautil.system
 * 文档描述：键盘工具类
 */
public class KeyBoardUtil {
    /**
     * [打开软键盘]
     * @param view 一般是EditText對象
     * @param mContext 上下文
     */
    public static void openKeybord(View view, Context mContext)
    {
        InputMethodManager imm = (InputMethodManager) mContext
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(view, InputMethodManager.RESULT_SHOWN);
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED,
                InputMethodManager.HIDE_IMPLICIT_ONLY);
        view.setFocusable(true);
        view.setFocusableInTouchMode(true);
        view.requestFocus();
    }

    /**
     * [关闭软键盘]
     * @param activity activity
     */
    public static void closeKeybord(Activity activity)
    {
        if (activity.getCurrentFocus() != null)
            ((InputMethodManager) activity
                    .getSystemService(Context.INPUT_METHOD_SERVICE))
                    .hideSoftInputFromWindow(activity.getCurrentFocus()
                            .getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
    }
}
