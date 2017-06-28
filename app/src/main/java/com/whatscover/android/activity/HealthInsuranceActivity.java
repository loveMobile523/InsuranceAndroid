package com.whatscover.android.activity;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.whatscover.android.R;
import com.whatscover.android.adapter.RecyclerAdapter;
import com.whatscover.android.constant.Constant;
import com.whatscover.android.model.ModelRecycler;
import com.whatscover.android.util.RecyclerOnItemClickListener;
import com.whatscover.android.utility.A;
import com.whatscover.android.utility.T;
import com.whatscover.android.webservice.ProductInterface;
import com.whatscover.android.webservice.authentication.ServiceGenerator;
import com.whatscover.android.webservice.dto.DTOProduct;
import com.whatscover.android.webservice.dto.DTOProductSuggestion;
import com.whatscover.android.webservice.dto.UserTest;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HealthInsuranceActivity extends BaseActivity{
    @BindView(R.id.btnViewDetails) Button btnViewDetails;
    @BindView(R.id.btnIFilter) ImageButton btnIFilter;
    @BindView(R.id.btnICompare) ImageButton btnICompare;
//    @BindView(R.id.btnIPlus) ImageButton btnIPlus;
    @BindView(R.id.liLayFilter) LinearLayout liLayFilter;
    @BindView(R.id.recycler_horizontal) RecyclerView recyclerView;
    List<DTOProductSuggestion> dtoProductSuggestions;

    private UserTest currentUser;

    private ProductInterface productInterface;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        btnViewDetails.setPaintFlags(btnViewDetails.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);

        currentUser = userManager.read();

        initialize();

        LinearLayoutManager layoutManager
                = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);

        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(R.string.title_health_insurances);

        dtoProductSuggestions = (List<DTOProductSuggestion>) getIntent().getSerializableExtra(Constant.DATA);


        if(dtoProductSuggestions!=null)
            initRecyclerView(false);

        // -----------------------------------------------------------------------------------------
        recyclerView.addOnItemTouchListener(new RecyclerOnItemClickListener(this, new RecyclerOnItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                // TODO ....
//                ModelRecycler modelRecycler = modelRecyclers.get(position);
//                String name = modelRecycler.getName();

                compareTwoProducts(1, 1);
            }
        }));
    }


    @Override
    protected int getLayout() {
        return R.layout.activity_health_insurances;
    }

    // ---------------------------------------------------------------------------------------------
    //
    public void initialize() {

        productInterface = ServiceGenerator.createService(ProductInterface.class, currentUser.getToken(), true);
    }

    @OnClick(R.id.btnViewDetails)
    public void onClickViewDetails() {
        A.goOtherActivity(context, PolicyDetailsActivity.class);
    }

    @OnClick(R.id.btnIFilter)
    public void onClickFilter() {
        initRecyclerView(false);
        liLayFilter.setVisibility(View.VISIBLE);
        btnICompare.setImageResource(R.drawable.ic_compare_off2);
        btnIFilter.setImageResource(R.drawable.ic_filter_on2);
    }

    @OnClick(R.id.btnICompare)
    public void onClickCompare() {
        initRecyclerView(true);
        liLayFilter.setVisibility(View.GONE);
        btnICompare.setImageResource(R.drawable.ic_compare_on2);
        btnIFilter.setImageResource(R.drawable.ic_filter_off2);
    }

    @OnClick(R.id.btnIPlus)
    public void onClickPlus() {
        A.goOtherActivity(context, AddNewPolicyActivity.class);
    }

    private void initRecyclerView(boolean selected) {
        List<ModelRecycler> modelRecyclers;
        ModelRecycler modelRecycler;

        // -----------------------------------------------------------------------------------------
        modelRecyclers = new ArrayList<>();
        modelRecycler = new ModelRecycler(1, "Direct Auto Insurance", "$1224", selected);
        modelRecyclers.add(modelRecycler);

        modelRecycler = new ModelRecycler(0, "Blue Cross Insurance", "$2000", selected);
        modelRecyclers.add(modelRecycler);

        modelRecycler = new ModelRecycler(2, "Humana Insurance", "$4000", selected);
        modelRecyclers.add(modelRecycler);

        RecyclerAdapter adapter = new RecyclerAdapter(this, dtoProductSuggestions);
        recyclerView.setAdapter(adapter);
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

    public void compareTwoProducts(long productIdOne, long productIdTwo){
        Call<List<DTOProduct>> call = productInterface.compareTwoProducts(productIdOne, productIdTwo);
        call.enqueue(new Callback<List<DTOProduct>>() {
            @Override
            public void onResponse(Call<List<DTOProduct>> call, Response<List<DTOProduct>> response) {
                if(!isError(response.code()) || response.isSuccessful()) {
                    List<DTOProduct> dtoProducts = response.body();

                    A.goOtherActivity(context, CompareActivity.class);
                } else {
                    T.showError(context, parseError(response.code()));
                }
            }

            @Override
            public void onFailure(Call<List<DTOProduct>> call, Throwable t) {
                T.showError(context, t.getMessage());
            }
        });

    }
}
