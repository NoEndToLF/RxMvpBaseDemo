package com.zhufaner.zhudashi.module.main.home.view;

import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zhufaner.zhudashi.R;
import com.zhufaner.zhudashi.base.view.BaseFragment;
import com.zhufaner.zhudashi.module.main.MainActivity;

/**
 * Created by static on 2017/12/21/021.
 */

public class HomeFragment extends BaseFragment{
    @Override
    protected boolean isHasToolbar() {
        return true;
    }

    @Override
    protected void showToolbar() {
        tv_toolbar_title.setText("首页");
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void loadData() {
        ((MainActivity)getParentActivity()).setReadPoint(0,5);
    }

    @Override
    protected void hasLoadVisible() {
        Log.v("loadDate","heihei");
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home_zds;
    }

}
