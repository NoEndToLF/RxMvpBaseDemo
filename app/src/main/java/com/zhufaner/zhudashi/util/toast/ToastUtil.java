package com.zhufaner.zhudashi.util.toast;

import android.widget.Toast;

import com.zhufaner.zhudashi.application.manager.AppManager;

import es.dmoral.toasty.Toasty;

/**
 * Created by radio on 2017/10/12.
 */

public class ToastUtil {

    public static void showSuccessToast(String message){
        Toasty.success(AppManager.getInstance().getContext(), message, Toast.LENGTH_SHORT, true).show();
    }
    public static void showInfoToast(String message){
        Toasty.info(AppManager.getInstance().getContext(), message, Toast.LENGTH_SHORT, true).show();
    }
    public static void showErrorToast(String message){
        Toasty.error(AppManager.getInstance().getContext(), message, Toast.LENGTH_SHORT, true).show();
    }
}
