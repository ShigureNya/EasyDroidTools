package me.jimhao.eorzeautil.http;

/**
 * 作者： guhaoran
 * 创建于： 2017/7/27
 * 包名： me.jimhao.eorzeautil.http
 * 文档描述：上传结果接口
 */
public interface UploadCallBack {
    String onUPLoadSuccess(String result);
    String onUploadFailed();
    String onError(Throwable e);
}
