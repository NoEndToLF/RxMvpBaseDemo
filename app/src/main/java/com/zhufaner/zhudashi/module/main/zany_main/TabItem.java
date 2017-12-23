package com.zhufaner.zhudashi.module.main.zany_main;

import android.content.Context;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.zhufaner.zhudashi.R;

import me.majiajie.pagerbottomtabstrip.internal.RoundMessageView;
import me.majiajie.pagerbottomtabstrip.item.BaseTabItem;

/**
 * Created by static on 2017/12/22/022.
 */

public class TabItem extends BaseTabItem{
    private ImageView imageView;
    private TextView roundMessageView;
    private int unSelectImg;
    private int SelectImg;
    public TabItem(Context context) {
        this(context,null);
    }

    public TabItem(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public TabItem(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        LayoutInflater.from(context).inflate(R.layout.tab_zds, this, true);
        imageView=(ImageView)findViewById(R.id.iv_tab);
        roundMessageView=(TextView)findViewById(R.id.messages_tab);

    }
    public void initialize(@DrawableRes int unSelectImg, @DrawableRes int SelectImg)
    {
        this.unSelectImg=unSelectImg;
        this.SelectImg=SelectImg;
    }
    @Override
    public void setChecked(boolean checked) {
    if (checked){
        imageView.setImageResource(SelectImg);
    }else{
        imageView.setImageResource(unSelectImg);
    }
    }

    @Override
    public void setMessageNumber(int number) {
        if (number>0){
            roundMessageView.setVisibility(View.VISIBLE);
            roundMessageView.setText(number+"");
            if (number>99){
                roundMessageView.setText("99+");
            }
        }else{
            roundMessageView.setVisibility(View.GONE);
        }
    }

    @Override
    public void setHasMessage(boolean hasMessage) {
    if (hasMessage){
        roundMessageView.setVisibility(View.VISIBLE);
    }else{
        roundMessageView.setVisibility(View.GONE);
    }
    }

    @Override
    public String getTitle() {
        return null;
    }
}
