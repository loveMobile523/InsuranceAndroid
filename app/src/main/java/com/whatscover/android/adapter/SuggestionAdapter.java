package com.whatscover.android.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.whatscover.android.R;
import com.whatscover.android.activity.HealthInsuranceActivity;
import com.whatscover.android.model.ModelRecycler;
import com.whatscover.android.model.ModelSuggestion;
import com.whatscover.android.util.RecyclerOnItemClickListener;

import java.util.ArrayList;

public class SuggestionAdapter extends BaseAdapter {

    RecyclerView recyclerView;
    boolean judge;

    Context context;
    ArrayList<ModelSuggestion> arrayList;

    public SuggestionAdapter(Context context, ArrayList<ModelSuggestion> arrayList){
        this.context = context;
        this.arrayList = arrayList;

        this.judge = true;
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null)
            convertView = LayoutInflater.from(context).inflate(R.layout.row_item_suggestion, null);

        final ModelSuggestion modelSuggestion = (ModelSuggestion) getItem(position);

        TextView tvSuggestionTitle = (TextView) convertView.findViewById(R.id.tvSuggestionTitle);
        tvSuggestionTitle.setText(modelSuggestion.getTitle());

        // -----------------------------------------------------------------------------------------
        this.recyclerView = (RecyclerView) convertView.findViewById(R.id.recycler_horizontal);

        LinearLayoutManager layoutManager
                = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        // -----------------------------------------------------------------------------------------

        ArrayList<ModelRecycler> modelRecyclers = new ArrayList<>();
        modelRecyclers = (ArrayList<ModelRecycler>) modelSuggestion.getModelRecyclers();

        /*RecyclerAdapter adapter = new RecyclerAdapter(context, modelRecyclers);
        recyclerView.setAdapter(adapter);*/

        // -----------------------------------------------------------------------------------------
        recyclerView.addOnItemTouchListener(new RecyclerOnItemClickListener(context, new RecyclerOnItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                // TODO ....
//                Toast.makeText(context, String.valueOf(position) + " selected", Toast.LENGTH_SHORT).show();

                if(judge) {
                    context.startActivity(new Intent(context, HealthInsuranceActivity.class));
                    judge = false;
                }

            }
        }));

//        recyclerView.scrollToPosition(position);


        return convertView;
    }

}
