package me.jimhao.eorzeautil.dialog;

import android.content.Context;

import cn.refactor.lib.colordialog.PromptDialog;

/**
 * 作者： guhaoran
 * 创建于： 2017/7/26
 * 包名： me.jimhao.eorzeautil.dialog
 * 文档描述：e
 */
public class MyPromptDialog extends PromptDialog {
    public MyPromptDialog(Context context) {
        super(context);
    }

    public MyPromptDialog setTitleContent(String title){
        setTitle(title);
        return this ;
    }

    public MyPromptDialog setSecondContent(String title){
        setContentText(title);
        return this ;
    }
}
