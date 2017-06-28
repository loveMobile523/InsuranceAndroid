package com.whatscover.android.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.whatscover.android.R;
import com.whatscover.android.webservice.dto.DTOProductSuggestion;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.CategoryHolder>{

    // ---------------------------------------------------------------------------------------------
    // suggestion and health insurance
    public static final Integer[] imgRecyclers = { R.drawable.ic_hospital_center4,
            R.drawable.ic_directauto_insurance_center3,
            R.drawable.ic_str3_center,
    };

    Context mContext;
    //ArrayList<ModelRecycler> itemData = new ArrayList<>();
    List<DTOProductSuggestion> dtoProductSuggestions;

    public RecyclerAdapter(Context mContext, List<DTOProductSuggestion> dtoProductSuggestions) {
        super();
        this.mContext = mContext;
        this.dtoProductSuggestions = dtoProductSuggestions;
    }

    @Override
    public CategoryHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.
                from(parent.getContext()).
                inflate(R.layout.row_item_recycler, parent, false);
        CategoryHolder viewHolder = new CategoryHolder(itemView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(CategoryHolder holder, int position) {

        /*if(modelRecycler.isJudgeSelected()) {
            holder.liLayCategoryBack.setBackgroundResource(R.drawable.mask_compare_on2);
        } else {
            holder.liLayCategoryBack.setBackgroundResource(R.drawable.mask_compare_off2);
        }*/

        holder.tvName.setText(dtoProductSuggestions.get(position).getCompanyName());
        holder.tvValue.setText(dtoProductSuggestions.get(position).getCompanyName());
        holder.imgCategoryBack.setImageResource(imgRecyclers[position]);
    }

    @Override
    public int getItemCount() {
        return this.dtoProductSuggestions.size();
    }

    public class CategoryHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.liLayCategoryBack) LinearLayout liLayCategoryBack;
        @BindView(R.id.imgCategoryBack) ImageView imgCategoryBack;
        @BindView(R.id.tvName) TextView tvName;
        @BindView(R.id.tvValue) TextView tvValue;

        public CategoryHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
