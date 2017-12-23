package com.zhufaner.zhudashi.application.manager;

import android.support.annotation.ColorRes;

/**
 * Created by static on 2017/12/21/021.
 */

public interface AppManagerImpl {
    /**相机相册*/
    void initImagePicker();
    /**Toast*/
    void initToastManager(@ColorRes int successColor, @ColorRes int infoColor, @ColorRes int ErrorColor, @ColorRes int textColor);
    /**网络请求封装，图片上传*/
    void initBaseLibrary();
    /**ViewPager图片浏览器*/
    void initImageVpShow();
    /**ListPopWindow初始化和网络图片加载*/
    void initListPopWindow(@ColorRes int PopWindowColor,@ColorRes int TextColor);
    /**Gif加载反馈框架初始化*/
    void initGifLoadRetryRefresh();
}
