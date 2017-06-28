package com.whatscover.android.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.whatscover.android.activity.AgentsOverviewActivity;
import com.whatscover.android.R;
import com.whatscover.android.adapter.AgentAdapter;
import com.whatscover.android.constant.Constant;

import butterknife.BindView;

public class FragmentAgentsOUpcoming extends BaseFragment {

    @BindView(R.id.recyclerAgentOUpcoming) RecyclerView recyclerAgentOUpcoming;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       super.onCreateView(inflater, container, savedInstanceState);
        // -----------------------------------------------------------------------------------------
        LinearLayoutManager layoutManager
                = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerAgentOUpcoming.setHasFixedSize(true);
        recyclerAgentOUpcoming.setLayoutManager(layoutManager);

        recyclerAgentOUpcoming.setAdapter(new AgentAdapter(getContext(), AgentsOverviewActivity.arrayList, Constant.FragmentAgentUpcoming));
        return rootView;
    }

    @Override
    int getLayout() {
        return R.layout.fragment_agents_overview_upcoming;
    }

}
