package com.zhufaner.zhudashi.module.main.mine.view;

import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zhufaner.zhudashi.R;
import com.zhufaner.zhudashi.base.view.BaseFragment;

/**
 * Created by static on 2017/12/21/021.
 */

public class MineFragment extends BaseFragment{


    @Override
    protected boolean isHasToolbar() {
        return true;
    }

    @Override
    protected void showToolbar() {
        tv_toolbar_title.setText("我的");

    }

    @Override
    protected void initView() {

    }

    @Override
    protected void loadData() {

    }

    @Override
    protected void hasLoadVisible() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_mine_zds;
    }


}
