package com.whatscover.android.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import com.whatscover.android.R;
import com.whatscover.android.model.ModelMember;
import com.whatscover.android.model.ModelRecycler;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MemberAdapter extends RecyclerView.Adapter<MemberAdapter.MemberHolder>{
    public static final Integer[] images = { R.drawable.dummy1_back5,
            R.drawable.dummy2_back5,
            R.drawable.dummy3_back5,
    };

    Context context;
    ArrayList<ModelMember> arrayList;

    public MemberAdapter(Context context, ArrayList<ModelMember> arrayList){
        this.context = context;
        this.arrayList = arrayList;
    }

    @Override
    public MemberHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.
                from(parent.getContext()).
                inflate(R.layout.row_item_member_select, parent, false);
        MemberHolder viewHolder = new MemberHolder(itemView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final MemberHolder holder, int position) {
        final ModelMember modelMember = arrayList.get(position);

        holder.imgDummy.setImageResource(images[modelMember.getImageId()]);

        holder.imgSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                modelMember.setJudgeSelected(!modelMember.isJudgeSelected());

                if(modelMember.isJudgeSelected())
                    holder.imgSelect.setBackgroundResource(R.drawable.checkbox_highlight);
                else
                    holder.imgSelect.setBackgroundResource(R.drawable.checkbox_normal);
            }
        });

        holder.tvName.setText(modelMember.getName());
    }

    @Override
    public int getItemCount() {
        return this.arrayList.size();
    }

    public class MemberHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.imgDummy) ImageView imgDummy;
        @BindView(R.id.imgSelect) ImageView imgSelect;
        @BindView(R.id.tvName) TextView tvName;

        public MemberHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
