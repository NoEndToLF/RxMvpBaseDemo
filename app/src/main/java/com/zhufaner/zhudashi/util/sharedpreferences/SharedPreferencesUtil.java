package com.zhufaner.zhudashi.util.sharedpreferences;

import android.content.Context;
import android.content.SharedPreferences;

import com.zhufaner.zhudashi.application.manager.AppManager;


/**
 * Created by radio on 2017/9/18.
 */
public class SharedPreferencesUtil {
    public static void saveString(String key,String value){
        SharedPreferences sharedPreferences = AppManager.getInstance().getContext().getSharedPreferences("wxy", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, value);
        editor.commit();
    }
    public static String getString(String key){
        SharedPreferences sharedPreferences = AppManager.getInstance().getContext().getSharedPreferences("wxy", Context.MODE_PRIVATE);
        String value= sharedPreferences.getString(key, "");
        return value;
    }
    public static void deleteAll(){
        SharedPreferences sharedPreferences = AppManager.getInstance().getContext().getSharedPreferences("wxy", Context.MODE_PRIVATE);
        sharedPreferences.edit().clear().commit();
    }
}
