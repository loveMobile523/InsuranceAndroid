package com.whatscover.android.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;


import com.whatscover.android.adapter.MemberAdapter;

import java.util.ArrayList;

import com.whatscover.android.R;

import com.whatscover.android.model.ModelMember;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AnpMembersSelectionFragment  extends BaseFragment {

    @BindView(R.id.recyclerMember) RecyclerView recyclerMember;
    ArrayList<ModelMember> arrayList;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    super.onCreateView(inflater, container, savedInstanceState);

        // -----------------------------------------------------------------------------------------
        LinearLayoutManager layoutManager
                = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerMember.setHasFixedSize(true);
        recyclerMember.setLayoutManager(layoutManager);

        arrayList = new ArrayList<>();
        ModelMember modelMember;

        arrayList.clear();
        modelMember = new ModelMember(1, "Name 01", false);
        arrayList.add(modelMember);

        modelMember = new ModelMember(2, "Name 02", false);
        arrayList.add(modelMember);

        modelMember = new ModelMember(0, "Name 03", false);
        arrayList.add(modelMember);

        recyclerMember.setAdapter(new MemberAdapter(getContext(), arrayList));

        return rootView;
    }

    @Override
    int getLayout() {
        return R.layout.fragment_anp_members_selection;
    }
}
