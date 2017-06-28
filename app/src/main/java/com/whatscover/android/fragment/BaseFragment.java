package com.whatscover.android.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.whatscover.android.R;
import com.whatscover.android.activity.BaseActivity;
import com.whatscover.android.webservice.authentication.UserManager;

import butterknife.ButterKnife;

/**
 * Created by arazfarhang on 30/04/2017.
 */

public abstract class BaseFragment extends Fragment {
    protected Context context;
    protected UserManager currentUser;
    BaseActivity parent;
    ViewGroup rootView;
    @Nullable
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getActivity();
        parent = (BaseActivity) getActivity();
        currentUser = UserManager.getInstance(context);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = (ViewGroup) inflater.inflate(getLayout(), container, false);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        parent = (BaseActivity) context;
    }

     abstract int getLayout();

}
