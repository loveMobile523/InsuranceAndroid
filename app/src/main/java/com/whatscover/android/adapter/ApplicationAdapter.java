package com.whatscover.android.adapter;

import android.content.Context;
import android.graphics.Paint;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.whatscover.android.model.ModelApplication;

import java.util.ArrayList;

import com.whatscover.android.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ApplicationAdapter extends RecyclerView.Adapter<ApplicationAdapter.ApplicationHolder>{
    public static final Integer[] imgDummys = { R.drawable.dummy2_back5,
            R.drawable.dummy3_back5,
            R.drawable.dummy1_back5,
    };

    public static final Integer[] imgCategories = { R.drawable.ic_hospital5,
            R.drawable.ic_payment5,
            R.drawable.ic_heart5,
    };

    Context context;
    ArrayList<ModelApplication> arrayList;

    public ApplicationAdapter(Context context, ArrayList<ModelApplication> arrayList){
        this.context = context;
        this.arrayList = arrayList;
    }

    @Override
    public ApplicationHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.
                from(parent.getContext()).
                inflate(R.layout.row_item_applied_application, parent, false);
        ApplicationHolder viewHolder = new ApplicationHolder(itemView);
        return viewHolder;
    }

    @Override
    public int getItemCount() {
        return this.arrayList.size();
    }

    @Override
    public void onBindViewHolder(final ApplicationHolder holder, int position) {
        final ModelApplication modelApplication = arrayList.get(position);

        holder.imgDummy.setImageResource(imgDummys[modelApplication.getImgDummy()]);
        holder.imgCategory.setImageResource(imgCategories[modelApplication.getImgCategory()]);

        holder.tvName.setText(modelApplication.getAgentName());
        holder.tvCategory.setText(modelApplication.getCategory());
        holder.tvStatus.setText(modelApplication.getApplicationStatus());

        holder.btnMessage.setPaintFlags(holder.btnMessage.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        holder.btnMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    public class ApplicationHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.imgDummy) ImageView imgDummy;
        @BindView(R.id.imgCategory) ImageView imgCategory;
        @BindView(R.id.tvName) TextView tvName;
        @BindView(R.id.tvCategory) TextView tvCategory;
        @BindView(R.id.tvStatus) TextView tvStatus;
        @BindView(R.id.btnMessage) Button btnMessage;

        public ApplicationHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
