package com.zhufaner.zhudashi.base.view;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.cloud.http.httprequestlife.LifeCycleListener;
import com.trello.rxlifecycle2.LifecycleTransformer;
import com.trello.rxlifecycle2.components.support.RxFragment;
import com.zhufaner.zhudashi.R;
import com.zhufaner.zhudashi.module.main.MainActivity;

import butterknife.ButterKnife;

/**
 * Created by static on 2017/12/21/021.
 */

public abstract class BaseFragment extends RxFragment {
    protected boolean isInit;
    protected boolean isLoad;
    private View rootView;
    private Activity activity;
    public LifeCycleListener mListener;
    protected Toolbar toolbar;
    protected LinearLayout linear_toolbar_back;
    protected ImageView iv_toolbar_back;
    protected TextView tv_toolbar_title;
    protected RelativeLayout relative_toolbar_right;
    protected ImageView iv_toolbar_right;
    protected TextView tv_toolbar_right;
    /**
     * 回调函数
     */
    public void setOnLifeCycleListener(LifeCycleListener listener) {
        mListener = listener;
    }
    public LifecycleTransformer bindLifecycle() {
        return bindToLifecycle();
    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        activity=(Activity)context;
    }
    public Activity getParentActivity(){
        return activity;
    }
    @Nullable
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.rootView = inflater.inflate(getLayoutId(),container,false);;
        ButterKnife.bind(this,rootView);
        if (isHasToolbar()){
            initToolbar();
            ((MainActivity)activity).setSupportActionBar(toolbar);
            showToolbar();
        }
        initView();
        return this.rootView;
    }
    private void initToolbar() {
        toolbar=(Toolbar)rootView.findViewById(R.id.toolbar);
        linear_toolbar_back=(LinearLayout)rootView.findViewById(R.id.linear_toolbar_back);
        iv_toolbar_back=(ImageView)rootView. findViewById(R.id.iv_toolbar_back);
        tv_toolbar_title=(TextView)rootView.findViewById(R.id.tv_toolbar_title);
        relative_toolbar_right=(RelativeLayout) rootView.findViewById(R.id.relative_toolbar_right);
        iv_toolbar_right=(ImageView)rootView.findViewById(R.id.iv_toolbar_right);
        tv_toolbar_right=(TextView)rootView.findViewById(R.id.tv_toolbar_right);
        linear_toolbar_back.setVisibility(View.INVISIBLE);
        relative_toolbar_right.setVisibility(View.INVISIBLE);
    }
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.isInit = true;
        this.isCanLoad();
    }
    protected abstract boolean isHasToolbar();
    protected abstract void showToolbar();
    protected abstract void initView();
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        this.isCanLoad();
    }

    private void isCanLoad() {
        if(this.isInit) {
            if(this.getUserVisibleHint()) {
                if(!this.isLoad) {
                    this.loadData();
                    this.isLoad = true;
                }else{
                    hasLoadVisible();
                }
            }
        }

    }

    public void onDestroyView() {
        super.onDestroyView();
        this.isInit = false;
        this.isLoad = false;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mListener!=null){
            mListener.onDestroy();
        }
    }

    protected abstract void loadData();
    protected abstract void hasLoadVisible();
    protected abstract int getLayoutId();
}
