package com.zhufaner.zhudashi.module.main.zany_main;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by static on 2017/12/22/022.
 */

public class MainPagerAadapter extends FragmentPagerAdapter {
    private List<Fragment> fragmentList;
    public MainPagerAadapter(FragmentManager fm,List<Fragment> fragmentList) {
        super(fm);
        this.fragmentList=fragmentList;
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }
}
