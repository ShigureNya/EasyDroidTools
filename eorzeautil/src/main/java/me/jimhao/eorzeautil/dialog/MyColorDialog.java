package me.jimhao.eorzeautil.dialog;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;

import cn.refactor.lib.colordialog.ColorDialog;

/**
 * 作者： guhaoran
 * 创建于： 2017/7/26
 * 包名： me.jimhao.eorzeautil.dialog
 * 文档描述：自建ColorDialog
 */
public class MyColorDialog extends ColorDialog {
    public MyColorDialog(Context context) {
        super(context);
    }

    /**
     * [设置标题]
     * @param title
     * @return
     */
    public MyColorDialog setTitleContent(String title){
        setTitle(title);
        return this ;
    }

    /**
     * [设置内容]
     * @param title
     * @return
     */
    public MyColorDialog setSecondContent(String title){
        setContentText(title);
        return this ;
    }

    public MyColorDialog setDialogImage(Bitmap bitmap){
        setContentImage(bitmap);
        return this ;
    }
    public MyColorDialog setDialogImage(Drawable drawable){
        setContentImage(drawable);
        return this;
    }

    public MyColorDialog setDialogImage(int res){
        setContentImage(res);
        return this;
    }
}
