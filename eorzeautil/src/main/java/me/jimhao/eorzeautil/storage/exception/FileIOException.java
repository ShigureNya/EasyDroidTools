package me.jimhao.eorzeautil.storage.exception;

import java.io.IOException;

import me.jimhao.eorzeautil.log.EasyLog;

/**
 * 作者： guhaoran
 * 创建于： 2017/7/28
 * 包名： me.jimhao.eorzeautil.storage.exception
 * 文档描述：文件流异常
 */
public class FileIOException extends IOException{
    public FileIOException(String msg){
        super(msg);
        EasyLog.i("遇到了文件流异常！");
    }
}
