package com.zhufaner.zhudashi.application.imagepicker;

import android.app.Activity;
import android.net.Uri;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.lzy.imagepicker.loader.ImageLoader;
import com.zhufaner.zhudashi.R;

import java.io.File;

/**
 * Created by radio on 2017/8/9.
 */

public class GlideImageLoader implements ImageLoader {

    @Override
    public void displayImage(Activity activity, String path, ImageView imageView, int width, int height) {
        Glide.with(activity)
                .load(Uri.fromFile(new File(path))).asBitmap()
                .placeholder(R.mipmap.imageplaceholder)
                .error(R.mipmap.imageplaceholder).into(imageView);
    }

    @Override
    public void displayImagePreview(Activity activity, String path, ImageView imageView, int width, int height) {
        Glide.with(activity)
                .load(Uri.fromFile(new File(path))) .asBitmap()
                .into(imageView);
    }

    @Override
    public void clearMemoryCache() {

    }
}
