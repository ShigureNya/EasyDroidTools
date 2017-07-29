# Easy Droid Tools
> 每次做项目的时候，总要Copy一大堆重复的工具类，着实麻烦。于是就有了上传一个自己常用的工具类的想法。

# 特性
> - 非常快捷的网络请求和文件操作
- 封装了开发中很常用的工具类和方法

# 下载安装

**Gradle**
```java
compile 'com.github.ShigureNya:EasyDroidTools:1.3.7'
```
**Maven**
```xml
<dependency>
	<groupId>com.github.ShigureNya</groupId>
	<artifactId>EasyDroidTools</artifactId>
	<version>1.3.7</version>
</dependency>
```
# 内容说明

### 常用工具

|工具|描述|
|---|:---:|
|EasyAnimation|动画工具类|
|EasyHttp|网络相关工具类|
|EasyBitmap|图像处理工具|
|EasyLog|日志类|
|EasyFile|文件访问类|
|EasyDroidSystem|系统工具|
|EasyActivity|Activity基类|
|EasyFragment|Fragment基类|
|EasyApplication|Application基类|


### 图像处理

|工具|描述|
|---|:---:|
|BitmapCompressUtil|处理图像压缩|
|ImageBlur|处理动态模糊|

### 系统工具

|工具|描述|
|---|:---:|
|ActivityTaskManager|Activity栈管理|
|CrashHandlerUtil|全局异常捕获|
|CameraUtil|照相机相关工具类|
|KeyBoardUtil|键盘工具类|
|MemoryStorageUtil|SharedPreference工具|
|VersionUtil|系统版本工具|
|VoiceUtil|录音相关工具类|
|TimeUtil|时间工具|

### 视图工具

|工具|描述|
|---|:---:|
|DialogUtil|生成Dialog提示框|
|SnackBarUtil|SnackBar的快速构建类|
|ToastUtil|吐司快速构建类|
|LoadStateLayout|四种加载模式的Layout|

### 逻辑操作

工具|描述
---|---
|JsonBuilder|Json构建工具|
|JsonParse|Json解析工具|
|MD5Util|将字符串转换为MD5编码|

# 使用方法
### 全局异常捕获

```java 
//让Application直接继承EasyApplication即可

//或者在Application中初始化CrashHandlerUtil
public class TestApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        CrashHandlerUtil util = CrashHandlerUtil.getInstance();
        util.setCrashTip("程序出现异常！");
        util.init(getApplicationContext());
    }
}
```

### LoadStateLayout 让Activity实现加载中、无数据、加载失败、加载成功
首先需要将View附加在Layout文件之中
```xml
<?xml version="1.0" encoding="utf-8"?>
<RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context="me.jimhao.eorzeaworld.MainActivity">
    <!-- 注意需要使用层叠类型的布局 -->
    <me.jimhao.eorzeautil.view.LoadStateLayout
        android:id="@+id/state"
        android:layout_centerInParent="true"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content">
    </me.jimhao.eorzeautil.view.LoadStateLayout>
</RelativeLayout>
```
使用setDisplayMode切换显示状态
```java
setDisplayMode(type);
```
### Activity栈管理
```java
//直接继承EasyActivity，内部实现了添加栈和弹出栈的功能

//在Activity中使用ActivityTaskManager
public class TestActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        //在onCreate时压入栈
        ActivityTaskManager.getInstance().addActivity(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //在销毁的时候弹出栈
        ActivityTaskManager.getInstance().removeActivity(this);
    }
}
```
软件完全退出
```java 
ActivityTaskManager.getInstance().byebye();
```

# 混淆注意
```jav 
#okhttp
-dontwarn okhttp3.**
-keep class okhttp3.**{*;}

#okio
-dontwarn okio.**
-keep class okio.**{*;}
```

# TODO
> 接下来可能会加一些Socket方面的工具类，还有Adapter的封装基类什么的

#License

> MIT License
Copyright (c) [2017] [EDT]
Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:<br/>
The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.<br/>
THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
