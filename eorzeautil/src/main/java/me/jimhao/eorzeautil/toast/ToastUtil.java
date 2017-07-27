package me.jimhao.eorzeautil.toast;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import me.jimhao.eorzeautil.R;

/**
 * 作者： guhaoran
 * 创建于： 2017/7/26
 * 包名： me.jimhao.eorzeautil.toast
 * 文档描述：Toast工具
 */
public class ToastUtil {
    /**
     * [显示Toast]
     * @param context 上下文
     * @param msg 消息内容
     */
    public static void showToast(Context context , String msg){
        LayoutInflater mInflater = LayoutInflater.from(context);
        View view = mInflater.inflate(R.layout.layout_custom_toast,null);
        TextView textview = (TextView) view.findViewById(R.id.toast_text);
        textview.setText(msg);
        Toast toast = new Toast(context);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(view);
        toast.show();
    }

    /**
     * [显示自定义Toast]
     * @param context 上下文
     * @param view 自定义的View对象
     */
    public static void showCustomToast(Context context , View view){
        Toast toast = new Toast(context);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(view);
        toast.show();
    }
}
