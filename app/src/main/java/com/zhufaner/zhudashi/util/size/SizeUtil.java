package com.zhufaner.zhudashi.util.size;

import android.content.Context;

/**
 * Created by static on 2017/12/22/022.
 */

public class SizeUtil {
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
}
