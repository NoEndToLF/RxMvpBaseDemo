package com.zhufaner.zhudashi.application;

import android.app.Application;

import com.zhufaner.zhudashi.R;
import com.zhufaner.zhudashi.application.manager.AppManager;

/**
 * Created by static on 2017/12/21/021.
 */

public class MyApplication extends Application{
    @Override
    public void onCreate() {
        super.onCreate();
        AppManager appManager=AppManager.getInstance();
        appManager.setContext(getApplicationContext());
        appManager.initBaseLibrary();/**网络请求封装，图片上传*/
        appManager.initImagePicker();/**相机相册*/
        appManager.initImageVpShow();/**ViewPager图片浏览器*/
        appManager.initListPopWindow(R.color.white,R.color.gray);/**常用PopupWindow*/
        appManager.initToastManager(R.color.Toast_Success,R.color.Toast_Info,
                R.color.Toast_Error,R.color.Toast_White);/**Toast*/
        appManager.initGifLoadRetryRefresh();/**Gif加载反馈框架初始化*/
    }
}
