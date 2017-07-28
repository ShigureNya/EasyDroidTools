package me.jimhao.eorzeautil.system;

import android.app.Activity;

import java.util.Stack;

/**
 * 作者： guhaoran
 * 创建于： 2017/7/28
 * 包名： me.jimhao.eorzeautil.system
 * 文档描述：Activity栈管理器
 */
public class ActivityTaskManager {
    private static ActivityTaskManager instance ;
    //Activity栈
    private Stack<Activity> activityStack = new Stack<Activity>();

    private ActivityTaskManager(){}

    public static ActivityTaskManager getInstance(){
        if(instance == null){
            instance = new ActivityTaskManager();
        }
        return instance ;
    }

    /**
     * [添加一个Activity到栈里]
     * @param activity activity
     */
    public void addActivity(Activity activity){
        activityStack.add(activity);
    }

    /**
     * [移除一个Activity]
     * @param activity activity
     */
    public void removeActivity(Activity activity){
        if (activityStack != null && activityStack.size() > 0) {
            if (activity != null) {
                activityStack.remove(activity);
                activity.finish();
            }
        }
    }

    /**
     * [获得栈中最后一个Activity(栈顶)]
     * @return activity
     */
    public Activity getLastActivity(){
        return activityStack.lastElement();
    }

    /**
     * [结束指定的Activity]
     * @param activity activity
     */
    public void finishActivity(Activity activity) {
        if (activity != null) {
            activityStack.remove(activity);
            activity.finish();
        }
    }

    /**
     * [结束所有栈中Activity]
     */
    public void finishAllActivity() {
        try {
            for (int i = 0; i < activityStack.size(); i++) {
                if (null != activityStack.get(i)) {
                    activityStack.get(i).finish();
                }
            }
            activityStack.clear();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * [退出应用程序]
     */
    public void byebye() {
        try {
            finishAllActivity();
            //退出JVM(java虚拟机),释放所占内存资源,0表示正常退出(非0的都为异常退出)
            System.exit(0);
            //从操作系统中结束掉当前程序的进程
            android.os.Process.killProcess(android.os.Process.myPid());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
