package me.jimhao.eorzeautil.toast;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.TextView;

import me.jimhao.eorzeautil.R;
import me.jimhao.eorzeautil.toast.exception.SnackBarException;

/**
 * 作者： guhaoran
 * 创建于： 2017/7/26
 * 包名： me.jimhao.eorzeautil.toast
 * 文档描述：SnackBar工具集
 */
public class SnackBarUtil {
    public static final int Alert = 1 ;
    public static final int Confirm = 2 ;
    public static final int Info = 3 ;
    public static final int Warning = 4 ;
    public static final int Error = 5 ;
    public static final int Custom = 0 ;

    public static class Builder{
        private Resources resources = null;
        private Snackbar snackbar = null ;
        public Builder(Context context){
            resources = context.getResources();
        }

        /**
         * @param layout 布局文件
         * @param message 消息内容
         * @param state 通知状态
         * @param backgroundColor 背景色
         * @return SnackBar对象
         */
        public Snackbar showDefaultSnackBar(View layout , String message , int state , int backgroundColor){
            snackbar = Snackbar.make(layout,message,Snackbar.LENGTH_SHORT);
            View layoutView = snackbar.getView();
            ((TextView) layoutView.findViewById(R.id.snackbar_text)).setTextColor(Color.WHITE);
            int defaultBackgroundColor = 0 ; //默认背景色
            switch (state){
                case Alert:
                    defaultBackgroundColor = resources.getColor(R.color.snackBar_alert) ;
                    break;
                case Confirm:
                    defaultBackgroundColor = resources.getColor(R.color.snackBar_confirm) ;
                    break;
                case Info:
                    defaultBackgroundColor = resources.getColor(R.color.snackBar_info) ;
                    break;
                case Warning:
                    defaultBackgroundColor = resources.getColor(R.color.snackBar_warning) ;
                    break;
                case Custom :
                    if(backgroundColor == 0){
                        try {
                            throw new SnackBarException("没有传值");
                        } catch (SnackBarException e) {
                            e.printStackTrace();
                        }
                    }
                    defaultBackgroundColor = backgroundColor;
                    break;
            }
            layoutView.setBackgroundColor(defaultBackgroundColor);

            return snackbar;
        }

        /**
         * 显示snackbar
         */
        public void show(){
            snackbar.show();
        }
    }
}
