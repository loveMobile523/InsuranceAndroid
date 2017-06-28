package com.whatscover.android.activity;

import android.app.Activity;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Button;


import com.whatscover.android.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CreateAgentsOActivity extends BaseActivity{

    @BindView(R.id.btnDone) Button btnDone;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        btnDone.setPaintFlags(btnDone.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_create_agents_overview;
    }

    @OnClick(R.id.liLayBack)
    public void onClickBack() {
        finish();
    }

    @OnClick(R.id.btnDone)
    public void onClickDone() {

    }
}
