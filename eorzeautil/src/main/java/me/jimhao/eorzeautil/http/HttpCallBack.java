package me.jimhao.eorzeautil.http;

/**
 * 作者： guhaoran
 * 创建于： 2017/7/27
 * 包名： me.jimhao.eorzeautil.http
 * 文档描述：Http请求接口
 */
public interface HttpCallBack {
    void onSuccess(String msg);
    void onFailed(Throwable e);
}
