package me.jimhao.eorzeaworld;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.LinearLayout;

import butterknife.InjectView;
import me.jimhao.eorzeautil.view.EasyActivity;
import me.jimhao.eorzeautil.view.LoadStateLayout;

public class MainActivity extends EasyActivity {
    @InjectView(R.id.bill_navi_layout)
    LinearLayout billNaviLayout;
    @InjectView(R.id.empty)
    LoadStateLayout empty;

    @Override
    public void initParams(Bundle params) {

    }

    @Override
    public void bindLayout() {
        //在此可使用DataBinding或setContentView绑定界面
        setContentView(R.layout.activity_main);
    }

    @Override
    public void initView(View view) {

    }

    @Override
    public void doBusiness(Context mContext) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);//模拟加载过程
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            //NETWORK_ERROR     NODATA    HIDE_LAYOUT  NETWORK_LOADING
                            empty.setDisplayMode(LoadStateLayout.NETWORK_ERROR);//错误页面

//                            empty.setErrorType(EmptyView.NODATA);//空页面

//                            empty.setErrorType(EmptyView.HIDE_LAYOUT);//有内容页面
//                            billNaviLayout.setVisibility(View.Visiable);//设置为显示
                        }
                    });
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        //点击事件
        empty.setOnLayoutClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                empty.setDisplayMode(LoadStateLayout.NETWORK_LOADING);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        empty.setDisplayMode(LoadStateLayout.NO_DATA);
                    }
                },1500);
            }
        });
    }

}
