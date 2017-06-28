package com.whatscover.android.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ListView;

import com.whatscover.android.R;
import com.whatscover.android.adapter.SuggestionAdapter;
import com.whatscover.android.model.ModelRecycler;
import com.whatscover.android.model.ModelSuggestion;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class SuggestionsActivity extends BaseActivity{
    @BindView(R.id.lvSuggestions) ListView lvSuggestions;

    ArrayList<ModelSuggestion> arrayList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(R.string.title_suggestion);

        arrayList = new ArrayList<>();
        ModelSuggestion modelSuggestion;

        List<ModelRecycler> modelRecyclers;
        ModelRecycler modelRecycler;

        arrayList.clear();

        // -----------------------------------------------------------------------------------------
        modelRecyclers = new ArrayList<>();
        modelRecycler = new ModelRecycler(1, "Direct Auto Insurance", "$1224", false);
        modelRecyclers.add(modelRecycler);

        modelRecycler = new ModelRecycler(0, "Blue Cross Insurance", "$2000", false);
        modelRecyclers.add(modelRecycler);

        modelRecycler = new ModelRecycler(2, "Humana Insurance", "$4000", false);
        modelRecyclers.add(modelRecycler);

        modelSuggestion = new ModelSuggestion("Accidental Disabilities", modelRecyclers);
        arrayList.add(modelSuggestion);

        // -----------------------------------------------------------------------------------------
        modelRecyclers = new ArrayList<>();
        modelRecycler = new ModelRecycler(1, "Direct Auto Insurance", "$1224", false);
        modelRecyclers.add(modelRecycler);

        modelRecycler = new ModelRecycler(2, "Humana Insurance", "$4000", false);
        modelRecyclers.add(modelRecycler);

        modelRecycler = new ModelRecycler(0, "Blue Cross Insurance", "$2000", false);
        modelRecyclers.add(modelRecycler);

        modelSuggestion = new ModelSuggestion("Critical Illnesses", modelRecyclers);
        arrayList.add(modelSuggestion);

        // -----------------------------------------------------------------------------------------
        modelRecyclers = new ArrayList<>();
        modelRecycler = new ModelRecycler(0, "Blue Cross Insurance", "$2000", false);
        modelRecyclers.add(modelRecycler);

        modelRecycler = new ModelRecycler(1, "Direct Auto Insurance", "$1224", false);
        modelRecyclers.add(modelRecycler);

        modelRecycler = new ModelRecycler(2, "Humana Insurance", "$4000", false);
        modelRecyclers.add(modelRecycler);

        modelSuggestion = new ModelSuggestion("Longterm Care", modelRecyclers);
        arrayList.add(modelSuggestion);

        // -----------------------------------------------------------------------------------------
        modelRecyclers = new ArrayList<>();
        modelRecycler = new ModelRecycler(1, "Direct Auto Insurance", "$1224", false);
        modelRecyclers.add(modelRecycler);

        modelRecycler = new ModelRecycler(0, "Blue Cross Insurance", "$2000", false);
        modelRecyclers.add(modelRecycler);

        modelRecycler = new ModelRecycler(2, "Humana Insurance", "$4000", false);
        modelRecyclers.add(modelRecycler);

        modelSuggestion = new ModelSuggestion("General Health", modelRecyclers);
        arrayList.add(modelSuggestion);

        // -----------------------------------------------------------------------------------------
        modelRecyclers = new ArrayList<>();
        modelRecycler = new ModelRecycler(1, "Direct Auto Insurance", "$1224", false);
        modelRecyclers.add(modelRecycler);

        modelRecycler = new ModelRecycler(2, "Humana Insurance", "$4000", false);
        modelRecyclers.add(modelRecycler);

        modelRecycler = new ModelRecycler(0, "Blue Cross Insurance", "$2000", false);
        modelRecyclers.add(modelRecycler);

        modelSuggestion = new ModelSuggestion("Investment Insurances", modelRecyclers);
        arrayList.add(modelSuggestion);

        lvSuggestions.setAdapter(new SuggestionAdapter(this, arrayList));
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_suggestions;
    }


    @Override
    protected void onResume() {
        lvSuggestions.setAdapter(new SuggestionAdapter(this, arrayList));
        super.onResume();
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
