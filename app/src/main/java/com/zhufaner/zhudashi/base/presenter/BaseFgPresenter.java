package com.zhufaner.zhudashi.base.presenter;

import com.android.cloud.http.httprequestlife.LifeCycleListener;
import com.trello.rxlifecycle2.LifecycleTransformer;
import com.zhufaner.zhudashi.base.view.BaseFragment;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;

/**
 * Created by radio on 2017/9/25.
 */

public class BaseFgPresenter<V,T> implements LifeCycleListener {
    protected Reference<V> mViewRef;
    protected V mView;
    protected Reference<T> mFragmentRef;
    protected T mFragment;
    public LifecycleTransformer getLifecycleTransformer() {
        return lifecycleTransformer;
    }

    protected LifecycleTransformer lifecycleTransformer;
    public BaseFgPresenter(V view, T Fragment) {
        attachView(view);
        attachFragment(Fragment);
        setListener(Fragment);
    }
    /**
     * 设置生命周期监听
     *
     * @author ZhongDaFeng
     */
    private void setListener(T Fragment) {
        if (getFragment() != null) {
            if (Fragment instanceof BaseFragment) {
                ((BaseFragment) getFragment()).setOnLifeCycleListener(this);
                lifecycleTransformer=((BaseFragment) getFragment()).bindLifecycle();
            }
        }
    }
    /**
     * 关联
     *
     * @param view
     */
    private void attachView(V view) {
        mViewRef = new WeakReference<V>(view);
        mView = mViewRef.get();
    }

    /**
     * 关联
     *
     * @param Fragment
     */
    private void attachFragment(T Fragment) {
        mFragmentRef = new WeakReference<T>(Fragment);
        mFragment = mFragmentRef.get();
    }

    /**
     * 销毁
     */
    private void detachView() {
        if (isViewAttached()) {
            mViewRef.clear();
            mViewRef = null;
        }
    }

    /**
     * 销毁
     */
    private void detachFragment() {
        if (isFragmentAttached()) {
            mFragmentRef.clear();
            mFragmentRef = null;
        }
    }

    /**
     * 获取
     *
     * @return
     */
    public V getView() {
        if (mViewRef == null) {
            return null;
        }
        return mViewRef.get();
    }

    /**
     * 获取
     *
     * @return
     */
    public T getFragment() {
        if (mFragmentRef == null) {
            return null;
        }
        return mFragmentRef.get();
    }

    /**
     * 是否已经关联
     *
     * @return
     */
    public boolean isViewAttached() {
        return mViewRef != null && mViewRef.get() != null;
    }

    /**
     * 是否已经关联
     *
     * @return
     */
    public boolean isFragmentAttached() {
        return mFragmentRef != null && mFragmentRef.get() != null;
    }

    @Override
    public void onDestroy() {
        detachView();
        detachFragment();
    }
}
