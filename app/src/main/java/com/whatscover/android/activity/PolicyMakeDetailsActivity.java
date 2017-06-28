package com.whatscover.android.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Button;

import com.whatscover.android.R;
import com.whatscover.android.utility.A;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PolicyMakeDetailsActivity extends BaseActivity {

    @BindView(R.id.btnSubmit) Button btnSubmit;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        btnSubmit.setPaintFlags(btnSubmit.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_policy_make_details;
    }

    @OnClick(R.id.liLayBack)
    public void onClickBack() {
        finish();
    }

    @OnClick(R.id.btnSubmit)
    public void onClickSubmit() {

        A.goOtherActivity(context, AgentsOverviewActivity.class);
    }

    @OnClick(R.id.imgLogo)
    public void onClickLogo() {

        A.goOtherActivity(context, AppliedAppActivity.class);
    }
}
