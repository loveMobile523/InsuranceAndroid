package com.whatscover.android.activity;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Button;

import com.whatscover.android.R;
import com.whatscover.android.utility.A;
import com.whatscover.android.utility.T;
import com.whatscover.android.webservice.ProductInterface;
import com.whatscover.android.webservice.authentication.ServiceGenerator;
import com.whatscover.android.webservice.dto.DTOApplyProductResponse;
import com.whatscover.android.webservice.dto.UserTest;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PolicyDetailsActivity extends BaseActivity{

    @BindView(R.id.btnApply) Button btnApply;

    private UserTest currentUser;

    private ProductInterface productInterface;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(R.string.title_insurance_detail);

        currentUser = userManager.read();

        initialize();

        btnApply.setPaintFlags(btnApply.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_policy_details;
    }

    // ---------------------------------------------------------------------------------------------
    //
    public void initialize() {

        productInterface = ServiceGenerator.createService(ProductInterface.class, currentUser.getToken(), true);
    }

    @OnClick(R.id.btnApply)
    public void onClickApply() {
        applyProduct(1);
    }
    
    public void applyProduct(long productId) {
        Call<DTOApplyProductResponse> call = productInterface.applyProduct(productId);
        call.enqueue(new Callback<DTOApplyProductResponse>() {
            @Override
            public void onResponse(Call<DTOApplyProductResponse> call, Response<DTOApplyProductResponse> response) {
                if( !isError(response.code()) || response.isSuccessful() ) {
                    DTOApplyProductResponse dtoApplyProductResponse = response.body();

                    A.goOtherActivity(context, PolicyMakeDetailsActivity.class);
                } else {
                    T.showError(context, parseError(response.code()));
                }
            }

            @Override
            public void onFailure(Call<DTOApplyProductResponse> call, Throwable t) {
                T.showError(context, t.getMessage());
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
