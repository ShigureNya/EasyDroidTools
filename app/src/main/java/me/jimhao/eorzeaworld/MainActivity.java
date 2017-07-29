package me.jimhao.eorzeaworld;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import me.jimhao.eorzeautil.view.EasyActivity;
import me.jimhao.eorzeautil.view.LoadStateLayout;

public class MainActivity extends EasyActivity {

    LoadStateLayout state;

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
        state.setDisplayMode(LoadStateLayout.NO_DATA);
    }

    @Override
    public void doBusiness(Context mContext) {

    }


    //直接把参数交给mHelper就行了
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

    }
}
