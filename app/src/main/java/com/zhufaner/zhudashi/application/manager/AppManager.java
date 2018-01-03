package com.zhufaner.zhudashi.application.manager;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;
import android.widget.ImageView;

import com.android.cloud.libraryinit.BaseLibraryManager;
import com.android.imageshowlibrary.ImageVpShowManager;
import com.android.imageshowlibrary.model.ImageVpType;
import com.android.listpoplibrary.ListPopWindowManager;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.github.chrisbanes.photoview.PhotoView;
import com.jaywei.mdprogress.CircularProgressBar;
import com.lzy.imagepicker.ImagePicker;
import com.wxystatic.loadretrylibrary.LoadReTryRefreshManager;
import com.wxystatic.loadretrylibrary.LoadRetryRefreshConfig;
import com.zhufaner.zhudashi.BuildConfig;
import com.zhufaner.zhudashi.R;
import com.zhufaner.zhudashi.api.NetApiService;
import com.zhufaner.zhudashi.application.imagepicker.GlideImageLoader;
import com.zhufaner.zhudashi.application.imagevpshow.ImageVpShowUtil;

import java.io.File;

import es.dmoral.toasty.Toasty;

/**
 * Created by static on 2017/12/21/021.
 */

public class AppManager implements AppManagerImpl{
    private static AppManager appManager;
    private Context context;
    public static AppManager getInstance(){
        if (appManager==null){
            synchronized (AppManager.class){
                if (appManager==null){
                    appManager=new AppManager();
                }
            }
        }
        return appManager;
    }
    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }
    @Override
    public void initImagePicker() {
        ImagePicker imagePicker = ImagePicker.getInstance();
        imagePicker.setImageLoader(new GlideImageLoader());   //设置图片加载器
        imagePicker.setShowCamera(true);  //显示拍照按钮
        imagePicker.setCrop(false);        //允许裁剪（单选才有效）
        imagePicker.setSaveRectangle(true); //是否按矩形区域保存
        imagePicker.setSelectLimit(9);    //选中数量限制
//        imagePicker.setStyle(CropImageView.Style.RECTANGLE);  //裁剪框的形状
//        imagePicker.setFocusWidth(800);   //裁剪框的宽度。单位像素（圆形自动取宽高最小值）
//        imagePicker.setFocusHeight(800);  //裁剪框的高度。单位像素（圆形自动取宽高最小值）
//        imagePicker.setOutPutX(1000);//保存文件的宽度。单位像素
//        imagePicker.setOutPutY(1000);//保存文件的高度。单位像素
    }

    @Override
    public void initToastManager(int successColor, int infoColor, int ErrorColor, int textColor) {
        Toasty.Config.getInstance().setSuccessColor(context.getResources().getColor(successColor))
                .setErrorColor(context.getResources().getColor(ErrorColor))
                .setInfoColor(context.getResources().getColor(infoColor))
                .setTextSize(14)
                .setTextColor(context.getResources().getColor(textColor)).apply();
    }

    @Override
    public void initBaseLibrary() {
        BaseLibraryManager<NetApiService> baseLibraryInitHelp=BaseLibraryManager.getInstance();
        //1--4顺序不能变
        //1,设置context
        baseLibraryInitHelp.setContext(context);
        //2,设置baseurl和证书
        baseLibraryInitHelp.setBaseUrl("www.baidu.com","www.baidu.com");
//        baseLibraryInitHelp.setHttpsCer(false);/**是否有证书,默认为false*/
        baseLibraryInitHelp.setHttpsCer(true);
        /**测试站和生产站证书，放在assets目录下*/
        baseLibraryInitHelp.setCerNames(new String[]{});
        //3,设置log开关
        baseLibraryInitHelp.setLogger(BuildConfig.isLog);
        //4,设置测试站还是生产站(可随时切换)
        baseLibraryInitHelp.setDebug(true);
        //其他设置
//        baseLibraryInitHelp.setUpLoadImgService();/**图片上传*/
//        baseLibraryInitHelp.setApiService(NetApiService.class);/**初始化Api*/
    }

    @Override
    public void initImageVpShow() {
        /**图片展示*/
        ImageVpShowManager.getInstance().setShowImageListener(new ImageVpShowManager.showImageListener() {
            @Override
            public void showImage(Context context, ImageVpType imageVpType, String path, PhotoView imageView, final CircularProgressBar progressBar) {
                if (imageVpType== ImageVpType.Local){
                    Glide.with(context).load(new File(path)).asBitmap().animate(R.anim.mylibrary_in_anim)
                            .listener(new RequestListener<File, Bitmap>() {
                                @Override
                                public boolean onException(Exception e, File model, Target<Bitmap> target, boolean isFirstResource) {
                                    return false;
                                }

                                @Override
                                public boolean onResourceReady(Bitmap resource, File model, Target<Bitmap> target, boolean isFromMemoryCache, boolean isFirstResource) {
                                    progressBar.setVisibility(View.GONE);
                                    return false;
                                }
                            })
                            .into(imageView);

                }else if (imageVpType== ImageVpType.Net){
                    Glide.with(context).load(path).asBitmap().animate(R.anim.mylibrary_in_anim)
                            .listener(new RequestListener<String, Bitmap>() {
                                @Override
                                public boolean onException(Exception e, String model, Target<Bitmap> target, boolean isFirstResource) {
                                    return false;
                                }

                                @Override
                                public boolean onResourceReady(Bitmap resource, String model, Target<Bitmap> target, boolean isFromMemoryCache, boolean isFirstResource) {
                                    progressBar.setVisibility(View.GONE);
                                    return false;
                                }
                            })
                            .into(imageView);
                }
            }
        });
        /**图片下载*/
        ImageVpShowManager.getInstance().setSaveImageListener(new ImageVpShowManager.saveImageListener() {
            @Override
            public void saveImage(Context context, String url, int position) {
                ImageVpShowUtil.savePicture(context,"zhufaner","zhufaner_"+System.currentTimeMillis(),url);
            }
        });
    }

    @Override
    public void initListPopWindow(int PopWindowColor, int TextColor) {
        ListPopWindowManager.getInStance().setPopWindowColor(PopWindowColor);
        ListPopWindowManager.getInStance().setTextColor(TextColor);
        ListPopWindowManager.getInStance().setShowImageListener(new ListPopWindowManager.showImageListener() {
            @Override
            public void showImage(Context context, String path, ImageView imageView) {
                Glide.with(context).load(path)
                        .asBitmap()
                        .centerCrop()
                        .into(imageView);
            }
        });
    }

    @Override
    public void initGifLoadRetryRefresh() {
        LoadRetryRefreshConfig config=new LoadRetryRefreshConfig();
        config.setBackgroundColor(R.color.white);
        config.setBtnNormalColor(R.color.btn_normal);
        config.setBtnPressedColor(R.color.btn_press);
//        config.setBtnBorderColor(R.color.oringe_normal);
        config.setBtnRadius(10f);
        config.setBtnText("点击重新加载");
        config.setLoadText(" 加载中 , 请稍候 . . .");
        config.setBtnTextColor(R.color.white);
        config.setLoadAndErrorTextColor(R.color.gray);
        config.setGif(R.drawable.zhufaner);
        LoadReTryRefreshManager.getInstance().setLoadRetryRefreshConfig(config);
    }
}
