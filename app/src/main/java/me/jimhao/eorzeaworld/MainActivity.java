package me.jimhao.eorzeaworld;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import java.util.HashMap;

import butterknife.InjectView;
import me.jimhao.eorzeautil.http.EasyHttp;
import me.jimhao.eorzeautil.http.HttpCallBack;
import me.jimhao.eorzeautil.log.NekoLog;
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
        String url = "http://221.226.80.114:8112/HeatCloudService.svc/GetBuildingData";
        HashMap<String,String> map = new HashMap<String,String>();
        map.put("companyId","32011498");
        map.put("communityId","none");
        EasyHttp.getInstance().doGet(url, map, new HttpCallBack() {
            @Override
            public void onSuccess(String msg) {
                NekoLog.i("测试接口请求成功",msg);
            }

            @Override
            public void onFailed(Throwable e) {
                NekoLog.e("测试接口请求失败",e.getMessage());
            }
        });
        EasyHttp.getInstance().disconnectOkHttp();
    }

}
