package com.zhufaner.zhudashi.base.view;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.cloud.http.httprequestlife.LifeCycleListener;
import com.android.dialoglibrary.UsefulDialogManager;
import com.android.listpoplibrary.ListPopWindowManager;
import com.trello.rxlifecycle2.LifecycleTransformer;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;
import com.wstatic.toolandstatusbarlibrary.ToolAndStatusBarMagager;
import com.wxystatic.loadretrylibrary.LoadReTryRefreshManager;
import com.zhufaner.zhudashi.R;
import com.zhufaner.zhudashi.util.toast.ToastType;
import com.zhufaner.zhudashi.util.toast.ToastUtil;

import org.w3c.dom.Text;

import butterknife.ButterKnife;

public abstract  class BaseActivity extends RxAppCompatActivity {
    public LifeCycleListener mListener;
    private Toolbar toolbar;
    private LinearLayout linear_toolbar_back;
    private ImageView iv_toolbar_back;
    private TextView tv_toolbar_title;
    private RelativeLayout relative_toolbar_right;
    private ImageView iv_toolbar_right;
    private TextView tv_toolbar_right;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        ButterKnife.bind(this);
        //横竖屏切换禁止
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        //沉浸式状态栏
        ToolAndStatusBarMagager.getInstance().setStatusBarColor(this, R.color.toolbar);
        //初始化Toolbar
        if (isHasToolbar()){
        initToolbar();
        setSupportActionBar(toolbar);
        }
    }

    private void initToolbar() {
        toolbar=(Toolbar)findViewById(R.id.toolbar);
        linear_toolbar_back=(LinearLayout)findViewById(R.id.linear_toolbar_back);
        iv_toolbar_back=(ImageView) findViewById(R.id.iv_toolbar_back);
        tv_toolbar_title=(TextView)findViewById(R.id.tv_toolbar_title);
        relative_toolbar_right=(RelativeLayout) findViewById(R.id.relative_toolbar_right);
        iv_toolbar_right=(ImageView)findViewById(R.id.iv_toolbar_right);
        tv_toolbar_right=(TextView)findViewById(R.id.tv_toolbar_right);
        linear_toolbar_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
//        linear_toolbar_back.setVisibility(View.INVISIBLE);
//        relative_toolbar_right.setVisibility(View.INVISIBLE);
    }

    protected abstract int getLayoutId();
    protected abstract boolean isHasToolbar();
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
    protected void onDestroy() {
        super.onDestroy();
        UsefulDialogManager.getInstance().onDestoryDialog(this);
        ListPopWindowManager.getInStance().onDestoryPopWindow(this);
        LoadReTryRefreshManager.getInstance().unRegister(this);
        if (mListener!=null){
        mListener.onDestroy();
        }
    }
    protected void showToast(ToastType toastType , String msg) {
        switch (toastType){
            case Info:
                ToastUtil.showInfoToast(msg);
                break;
            case Error:
                ToastUtil.showErrorToast(msg);
                break;
            case Success:
                ToastUtil.showSuccessToast(msg);
                break;
        }
    }
}
