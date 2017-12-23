package com.zhufaner.zhudashi.base.view;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.cloud.http.httprequestlife.LifeCycleListener;
import com.trello.rxlifecycle2.LifecycleTransformer;
import com.trello.rxlifecycle2.components.support.RxFragment;

/**
 * Created by static on 2017/12/21/021.
 */

public abstract class BaseFragment extends RxFragment {
    protected boolean isInit;
    protected boolean isLoad;
    private View rootView;
    private Activity activity;
    public LifeCycleListener mListener;
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
        this.rootView = this.setFragmentView(inflater, container);
        return this.rootView;
    }

    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.isInit = true;
        this.isCanLoad();
    }

    protected abstract View setFragmentView(LayoutInflater var1, @Nullable ViewGroup var2);

    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        this.isCanLoad();
        if (getUserVisibleHint()){
            fgVisible();
        }
    }

    private void isCanLoad() {
        if(this.isInit) {
            if(this.getUserVisibleHint()) {
                if(!this.isLoad) {
                    this.loadData();
                    this.isLoad = true;
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
    protected abstract void fgVisible();
}
