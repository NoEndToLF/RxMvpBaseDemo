package com.zhufaner.zhudashi.module.main;

import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.zhufaner.zhudashi.R;
import com.zhufaner.zhudashi.base.view.BaseActivity;
import com.zhufaner.zhudashi.module.main.home.view.HomeFragment;
import com.zhufaner.zhudashi.module.main.mine.view.MineFragment;
import com.zhufaner.zhudashi.module.main.worksite.view.WorkSiteFragment;
import com.zhufaner.zhudashi.module.main.zany_main.MainPagerAadapter;
import com.zhufaner.zhudashi.module.main.zany_main.TabItem;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import me.majiajie.pagerbottomtabstrip.NavigationController;
import me.majiajie.pagerbottomtabstrip.PageNavigationView;

public class MainActivity extends BaseActivity {

    @BindView(R.id.main_viewpager)
    ViewPager mainViewpager;
    @BindView(R.id.main_tablayout)
    PageNavigationView mainTabLayout;
    private int[] mIconUnselectIds = {R.mipmap.home_unselect, R.mipmap.worksite_unselect, R.mipmap.mine_unselect};
    private int[] mIconSelectIds = {R.mipmap.home_select, R.mipmap.worksite_select, R.mipmap.mine_select};
    private List<Fragment> fragmentList;
    private NavigationController navigationController;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initFragment();
        initTabAndViewpager();
    }
    public void setReadPoint(int position,int count){
        navigationController.setMessageNumber(position,count);
    }
    private void initTabAndViewpager() {
        navigationController = mainTabLayout.custom()
                .addItem(newItem(mIconUnselectIds[0],mIconSelectIds[0]))
                .addItem(newItem(mIconUnselectIds[1],mIconSelectIds[1]))
                .addItem(newItem(mIconUnselectIds[2],mIconSelectIds[2]))
                .build();
        mainViewpager.setAdapter(new MainPagerAadapter(getSupportFragmentManager(),fragmentList));
        navigationController.setupWithViewPager(mainViewpager);
    }

    private void initFragment() {
        fragmentList=new ArrayList<>();
        fragmentList.add(new HomeFragment());
        fragmentList.add(new WorkSiteFragment());
        fragmentList.add(new MineFragment());
    }
    private TabItem newItem(@DrawableRes int unSelectImg, @DrawableRes int SelectImg){
        TabItem tabItem = new TabItem(this);
        if (unSelectImg!=mIconUnselectIds[0]){
            tabItem.setHasMessage(false);
        }
        tabItem.initialize(unSelectImg,SelectImg);
        return tabItem;
    }
    @Override
    protected int getLayoutId() {
        return R.layout.activity_main_zds;
    }

    @Override
    protected boolean isHasToolbar() {
        return true;
    }
}
