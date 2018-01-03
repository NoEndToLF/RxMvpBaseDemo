package com.zhufaner.zhudashi.base.presenter;

import com.android.cloud.http.httprequestlife.LifeCycleListener;
import com.android.cloud.libraryinit.BaseLibraryManager;
import com.trello.rxlifecycle2.LifecycleTransformer;
import com.zhufaner.zhudashi.api.NetApiService;
import com.zhufaner.zhudashi.base.view.BaseActivity;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;

/**
 * Created by radio on 2017/9/25.
 */

public class BaseAcPresenter<V,T> implements LifeCycleListener {
    protected NetApiService netApiService;
    protected Reference<V> mViewRef;
    protected V mView;
    protected Reference<T> mActivityRef;
    protected T mActivity;
    public LifecycleTransformer getLifecycleTransformer() {
        return lifecycleTransformer;
    }

    protected LifecycleTransformer lifecycleTransformer;
    public BaseAcPresenter(V view, T activity) {
        BaseLibraryManager<NetApiService> baseLibraryInitHelp=BaseLibraryManager.getInstance();
        netApiService=baseLibraryInitHelp.getNetService();
        attachView(view);
        attachActivity(activity);
        setListener(activity);
    }
    /**
     * 设置生命周期监听
     *
     * @author ZhongDaFeng
     */
    private void setListener(T activity) {
        if (getActivity() != null) {
            if (activity instanceof BaseActivity) {
                ((BaseActivity) getActivity()).setOnLifeCycleListener(this);
                lifecycleTransformer=((BaseActivity) getActivity()).bindLifecycle();
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
     * @param activity
     */
    private void attachActivity(T activity) {
        mActivityRef = new WeakReference<T>(activity);
        mActivity = mActivityRef.get();
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
    private void detachActivity() {
        if (isActivityAttached()) {
            mActivityRef.clear();
            mActivityRef = null;
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
    public T getActivity() {
        if (mActivityRef == null) {
            return null;
        }
        return mActivityRef.get();
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
    public boolean isActivityAttached() {
        return mActivityRef != null && mActivityRef.get() != null;
    }

    @Override
    public void onDestroy() {
        detachView();
        detachActivity();
    }
}
