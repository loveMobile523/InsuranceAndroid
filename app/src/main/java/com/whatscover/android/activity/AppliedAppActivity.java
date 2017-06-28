package com.whatscover.android.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.whatscover.android.R;
import com.whatscover.android.model.ModelApplication;

import java.util.ArrayList;

import com.whatscover.android.adapter.ApplicationAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AppliedAppActivity extends BaseActivity{
    @BindView(R.id.recyclerApplication) RecyclerView recyclerApplication;

    ArrayList<ModelApplication> arrayList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_applied_application);
        ButterKnife.bind(this);

        // -----------------------------------------------------------------------------------------
        LinearLayoutManager layoutManager
                = new LinearLayoutManager(AppliedAppActivity.this, LinearLayoutManager.VERTICAL, false);
        recyclerApplication.setHasFixedSize(true);
        recyclerApplication.setLayoutManager(layoutManager);

        arrayList = new ArrayList<>();
        ModelApplication modelApplication;

        arrayList.clear();
        modelApplication = new ModelApplication(0, "John Smith", 0, "Accidental Disabilities", "Reviewing Documents");
        arrayList.add(modelApplication);

        modelApplication = new ModelApplication(1, "Mel Gilbert", 1, "Investment Insurance", "Rejected");
        arrayList.add(modelApplication);

        modelApplication = new ModelApplication(2, "Sam Norval", 2, "General Health", "Appointment on 01/12/17-12:00pm");
        arrayList.add(modelApplication);

        recyclerApplication.setAdapter(new ApplicationAdapter(this, arrayList));
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_applied_application;
    }

    @OnClick(R.id.liLayBack)
    public void onClickBack() {
        finish();
    }
}
