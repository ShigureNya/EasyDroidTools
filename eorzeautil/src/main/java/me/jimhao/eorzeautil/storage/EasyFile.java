package me.jimhao.eorzeautil.storage;

import android.os.Environment;
import android.util.Base64;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.NoSuchAlgorithmException;

import me.jimhao.eorzeautil.log.EasyLog;
import me.jimhao.eorzeautil.storage.exception.FileNotFoundException;

/**
 * 作者： guhaoran
 * 创建于： 2017/7/28
 * 包名： me.jimhao.eorzeautil.storage
 * 文档描述：轻松访问工具类
 */
public class EasyFile{
    /**
     * [获取文件对象]
     * @param filePath 文件路径
     * @return 文件对象
     */
    public static File getFile(String filePath){
        return new File(filePath);
    }

    /**
     * [得到文件名]
     * @param file 文件对象
     * @return 文件名
     */
    public static String getFileName(File file){
        return file.getName();
    }

    /**
     * [得到文件名]
     * @param filePath 文件地址
     * @return 文件名
     */
    public static String getFileName(String filePath){
        File file = getFile(filePath);
        return getFileName(file);
    }

    /**
     * [得到文件大小]
     * @param filePath 文件地址
     * @return 文件大小
     */
    public static long getFileSize(String filePath){
        File file = getFile(filePath);
        if(getFileExists(file)){
            return file.length();
        }else{
            return -1 ;
        }
    }

    /**
     * [得到文件大小]
     * @param file 文件对象
     * @return 文件大小
     */
    public static long getFileSize(File file){
        if(getFileExists(file)){
            return file.length();
        }else{
            return -1 ;
        }
    }

    /**
     * [得到文件地址]
     * @param file 文件对象
     * @return 文件地址
     */
    public static String getFilePath(File file) {
        return file.getAbsolutePath();
    }

    /**
     * [得到文件拓展名]
     * @param file 文件对象
     * @return 拓展名
     */
    public static String getExtension(File file) {
        String fileName = getFileName(file);
        return fileName.substring(fileName.lastIndexOf(".")+1);
    }

    /**
     * [文件是否存在]
     * @param file 文件对象
     * @return 是否存在
     */
    public static boolean getFileExists(File file) {
        if (!file.exists() || !file.isFile()) {
            try {
                throw new FileNotFoundException(file , "未找到该文件");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            return false ;
        }
        return true ;
    }

    /**
     * [得到SD卡根目录]
     * @return 根目录
     */
    public static File getExternalStoragePath(){
        boolean flag = getExternalStorageState();
        if(flag){
            return Environment.getExternalStorageDirectory();
        }else{
            return null;
        }
    }

    /**
     * [得到SD卡状态 是否存在SD卡]
     * @return SD卡状态
     */
    public static boolean getExternalStorageState(){
        if (android.os.Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED)) {
            return true ;
        }
        return false ;
    }




    /**
     * File构建单元对象
     */
    public static class FileBuilder{
        private FileInfo fileInfo;
        public FileBuilder(String filePath){
            File file = new File(filePath);
            init(file);
        }
        public FileBuilder(File file){
            init(file); //构造方法初始化文件对象
        }
        private void init(File file){
            String name = getFileName(file);
            String extension = getExtension(file);
            long size = getFileSize(file);
            String path = getFilePath(file);

            fileInfo.setFile(file);
            fileInfo.setExtension(extension);
            fileInfo.setName(name);
            fileInfo.setSize(size);
            fileInfo.setPath(path);
        }
        /**
         * [构建file实体]
         * @return 实体
         */
        public FileInfo create(){
            return fileInfo;
        }

        /**
         * [将文件名转换为MD5]
         * @return MD5值
         */
        public String fileNametoMD5(){
            String fileName = fileInfo.getName();
            String md5Code = null ;
            try {
                md5Code =  MD5Util.getMD5(fileName);
            } catch (NoSuchAlgorithmException e) {
                EasyLog.e("MD5转码失败");
                md5Code = null ;
            }
            return md5Code ;
        }

        /**
         * [文件转base64编码]
         * @return base64码
         */
        public String fileToBase64(){
            String base64 = null;
            InputStream in = null;
            try {
                in = new FileInputStream(fileInfo.getFile());
                byte[] bytes = new byte[in.available()];
                int length = in.read(bytes);
                base64 = Base64.encodeToString(bytes, 0, length, Base64.DEFAULT);
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (in != null) {
                        in.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return base64;
        }


        /**
         * [文件转字节数组]
         * @return 字节数组
         */
        public byte[] fileToByteArray(){
            byte[] bytes = null ;
            InputStream in = null;
            try {
                in = new FileInputStream(fileInfo.getFile());
                bytes = new byte[in.available()];
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (in != null) {
                        in.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return bytes;
        }
    }

}
