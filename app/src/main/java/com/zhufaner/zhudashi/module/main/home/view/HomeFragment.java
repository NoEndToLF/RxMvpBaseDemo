package com.zhufaner.zhudashi.module.main.home.view;

import android.support.annotation.Nullable;
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
    protected View setFragmentView(LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup) {
        View view=layoutInflater.inflate(R.layout.fragment_home_zds,viewGroup,false);
        ((MainActivity)getParentActivity()).setReadPoint(0,5);
        return view;
    }

    @Override
    protected void loadData() {

    }

    @Override
    protected void fgVisible() {

    }
}
