package me.jimhao.eorzeautil.dialog;

import android.content.Context;

import me.jimhao.eorzeautil.dialog.exception.DialogException;

/**
 * 作者： guhaoran
 * 创建于： 2017/7/26
 * 包名： me.jimhao.eorzeautil.dialog
 * 文档描述：对话框工具
 */
public class DialogUtil{
    //默认通知标题
    private static final String DEFAULT_NOTIFICATION_TITLE = "通知";
    private static final String DEFAULT_NOTIFICATION_CONTENT = "内容";
    /**
     * 普通Dialog的构建类
     */
    public static class DialogBuilder{
        private MyColorDialog dialog ;
        public DialogBuilder(Context context){
            dialog = new MyColorDialog(context);
        }

        public MyColorDialog create(){
            return dialog ;
        }

        public void show(){ dialog.show(); }
        public void dismiss(){
            dialog.dismiss();
        }

        /**
         * [构建普通提示框的Dialog]
         * @param info Dialog内容实体
         * @return Dialog对象
         */
        public MyColorDialog buildDialog(DialogInfo info){
            if(info == null){
                try {
                    throw new DialogException("Dialog不能为空");
                } catch (DialogException e) {
                    e.printStackTrace();
                }
            }
            String title = info.getTitle() != null ? info.getTitle() : DEFAULT_NOTIFICATION_TITLE;
            String content = info.getContent() != null ? info.getContent() : DEFAULT_NOTIFICATION_CONTENT ;
            dialog.setTitleContent(title).setSecondContent(content);
            if(info.getHeadImage() != null){
                dialog.setDialogImage(info.getHeadImage());
            }
            return dialog ;
        }
    }

    /**
     * 确认提示框的构建类
     */
    public static class PromptDialogBuilder{
        private MyPromptDialog dialog ;
        public PromptDialogBuilder(Context context){dialog = new MyPromptDialog(context);}

        public MyPromptDialog create(){return dialog ;}
        public void show(){ dialog.show(); }
        public void dismiss(){
            dialog.dismiss();
        }
        /**
         * [构建确认提示框的Dialog]
         * @param info Dialog内容实体
         * @return Dialog对象
         */
        public MyPromptDialog buildDialog(DialogInfo info){
            if(info == null){
                try {
                    throw new DialogException("Dialog不能为空");
                } catch (DialogException e) {
                    e.printStackTrace();
                }
            }
            String title = info.getTitle() != null ? info.getTitle() : DEFAULT_NOTIFICATION_TITLE;
            String content = info.getContent() != null ? info.getContent() : DEFAULT_NOTIFICATION_CONTENT ;
            int dialogType = info.getDialogType();
            dialog.setTitleContent(title).setSecondContent(content).setPromptDialogType(dialogType);
            return dialog ;
        }
    }
}
