package com.whatscover.android.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.whatscover.android.activity.CreateAgentsOActivity;
import com.whatscover.android.R;
import com.whatscover.android.constant.Constant;
import com.whatscover.android.model.ModelAgent;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AgentAdapter extends RecyclerView.Adapter<AgentAdapter.AgentHolder>{
    private static final Integer[] imgDummys = { R.drawable.dummy1_back5,
            R.drawable.dummy2_back5,
            R.drawable.dummy3_back5,
    };

    Context context;
    ArrayList<ModelAgent> arrayList;
    String typeStr;

    public AgentAdapter(Context context, ArrayList<ModelAgent> arrayList, String typeStr){
        this.context = context;
        this.arrayList = arrayList;
        this.typeStr = typeStr;
    }

    @Override
    public AgentHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.
                from(parent.getContext()).
                inflate(R.layout.row_item_agents_o_new, parent, false);
        AgentHolder viewHolder = new AgentHolder(itemView);
        return viewHolder;
    }

    @Override
    public int getItemCount() {
        return this.arrayList.size();
    }

    @Override
    public void onBindViewHolder(final AgentHolder holder, int position) {
        final ModelAgent modelAgent = arrayList.get(position);

        holder.imgDummy.setImageResource(imgDummys[modelAgent.getImgDummy()]);

        holder.tvApplicantTitle.setText(modelAgent.getApplicationTitle());
        holder.tvAgentName.setText(modelAgent.getAgentName());
        holder.tvCoverage.setText(modelAgent.getProductCoverage());
        holder.tvAppointment.setText(modelAgent.getAppointment());
        holder.tvPercent.setText(modelAgent.getPercent() + "% Completed");

        holder.progressBarPercent.setProgress(Integer.parseInt(modelAgent.getPercent()));

        holder.liLayProductCoverage.setVisibility(View.GONE);
        holder.liLayAppointment.setVisibility(View.GONE);
        holder.liLayPercent.setVisibility(View.GONE);

        if(typeStr.equals(Constant.FragmentAgentNew)) {
            holder.liLayProductCoverage.setVisibility(View.VISIBLE);
        } else if(typeStr.equals(Constant.FragmentAgentUpcoming)) {
            holder.liLayAppointment.setVisibility(View.VISIBLE);
        } else if(typeStr.equals(Constant.FragmentAgentFollowUp)) {
            holder.liLayPercent.setVisibility(View.VISIBLE);
        }

        holder.btnMessage.setPaintFlags(holder.btnMessage.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        holder.btnMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context, CreateAgentsOActivity.class));
            }
        });
    }

    public class AgentHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.imgDummy) ImageView imgDummy;
        @BindView(R.id.tvApplicantTitle) TextView tvApplicantTitle;
        @BindView(R.id.tvAgentName) TextView tvAgentName;
        @BindView(R.id.tvCoverage) TextView tvCoverage;
        @BindView(R.id.tvAppointment) TextView tvAppointment;
        @BindView(R.id.tvPercent) TextView tvPercent;

        @BindView(R.id.progressBarPercent) ProgressBar progressBarPercent;

        @BindView(R.id.liLayProductCoverage) LinearLayout liLayProductCoverage;
        @BindView(R.id.liLayAppointment) LinearLayout liLayAppointment;
        @BindView(R.id.liLayPercent) LinearLayout liLayPercent;

        @BindView(R.id.btnMessage) Button btnMessage;

        public AgentHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
