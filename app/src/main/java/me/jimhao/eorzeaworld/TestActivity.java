package me.jimhao.eorzeaworld;

import android.app.Activity;
import android.os.Bundle;
import android.os.PersistableBundle;

import me.jimhao.eorzeautil.system.ActivityTaskManager;

/**
 * 作者： guhaoran
 * 创建于： 2017/7/29
 * 包名： me.jimhao.eorzeaworld
 * 文档描述：
 */
public class TestActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        //在onCreate时压入栈
        ActivityTaskManager.getInstance().addActivity(this);

        //退出程序
        ActivityTaskManager.getInstance().byebye();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //在销毁的时候弹出栈
        ActivityTaskManager.getInstance().removeActivity(this);
    }
}
