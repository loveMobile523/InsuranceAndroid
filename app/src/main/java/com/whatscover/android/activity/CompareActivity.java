package com.whatscover.android.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.whatscover.android.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class CompareActivity extends BaseActivity{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected int getLayout() {
        return R.layout.activity_compare;
    }

    @OnClick(R.id.liLayBack)
    public void onClickBack() {
        finish();
    }
}
