package me.jimhao.eorzeaworld;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import butterknife.InjectView;
import butterknife.OnClick;
import me.jimhao.eorzeautil.view.EasyActivity;

public class MainActivity extends EasyActivity {

    @InjectView(R.id.image)
    ImageView image;
    @InjectView(R.id.btn)
    Button btn;

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

    }

    private float index = 1 ;
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @OnClick(R.id.btn)
    public void onViewClicked() {

    }

}
