package com.zhufaner.zhudashi.module.main.worksite.view;

import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zhufaner.zhudashi.R;
import com.zhufaner.zhudashi.base.view.BaseFragment;

/**
 * Created by static on 2017/12/21/021.
 */

public class WorkSiteFragment extends BaseFragment{
    protected View setFragmentView(LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup) {
        View view=layoutInflater.inflate(R.layout.fragment_worksite_zds,viewGroup,false);
        return view;
    }

    @Override
    protected void loadData() {

    }

    @Override
    protected void fgVisible() {

    }
}
