package me.jimhao.eorzeautil.http;

/**
 * 作者： guhaoran
 * 创建于： 2017/7/27
 * 包名： me.jimhao.eorzeautil.http
 * 文档描述：下载接口
 */
public interface DownloadCallBack {
    /**
     * 下载成功
     */
    void onDownloadSuccess(String path);

    /**
     * @param progress
     * 下载进度
     */
    void onDownloading(int progress);

    /**
     * 下载失败
     */
    void onDownloadFailed();
}
