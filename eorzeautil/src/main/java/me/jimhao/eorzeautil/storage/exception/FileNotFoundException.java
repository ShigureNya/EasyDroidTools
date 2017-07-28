package me.jimhao.eorzeautil.storage.exception;

import java.io.File;

import me.jimhao.eorzeautil.log.EasyLog;

/**
 * 作者： guhaoran
 * 创建于： 2017/7/28
 * 包名： me.jimhao.eorzeautil.storage.exception
 * 文档描述：未找到文件
 */
public class FileNotFoundException extends java.io.FileNotFoundException {
    public FileNotFoundException(File file ,String msg){
        super();
        EasyLog.e("文件名:"+file.getAbsolutePath(),msg);
    }
}
