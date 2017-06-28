package com.whatscover.android.activity;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;

import com.whatscover.android.R;
import com.whatscover.android.helpers.ActivityBuilder;
import com.whatscover.android.security.TextValidator;
import com.whatscover.android.utility.SystemBarTintManager;
import com.whatscover.android.webservice.APIClient;
import com.whatscover.android.webservice.APIInterface;
import com.whatscover.android.webservice.authentication.UserManager;

import java.net.HttpURLConnection;
import java.util.List;

import butterknife.ButterKnife;

/**
 * Created by arazfarhang on 30/04/2017.
 */

public abstract class BaseActivity extends AppCompatActivity {
    long[] mHits = new long[2];
    APIInterface apiInterface;
    Activity currentActivity;
    protected Context context;
    UserManager userManager;
    TextValidator mAwesomeValidation;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        apiInterface = APIClient.getClient().create(APIInterface.class);
        context = this;
        currentActivity = this;
        userManager = UserManager.getInstance(getApplicationContext());
        initLayout();
        ButterKnife.bind(this);
        mAwesomeValidation = TextValidator.createTextValidator(context);

        // currentUser = userManager.getCurrentUser();
        DisplayMetrics metric = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metric);
    }

    public void initmAwesomeValidation(){
        mAwesomeValidation=null;
        mAwesomeValidation = TextValidator.createTextValidator(context);
    }
    public  String parseError(int errorCode){

        if (errorCode == HttpURLConnection.HTTP_INTERNAL_ERROR){

            return getString(R.string.error_internal);

        } else if (errorCode == HttpURLConnection.HTTP_BAD_REQUEST){

            return getString(R.string.error_bad_request);

        } else if (errorCode == HttpURLConnection.HTTP_FORBIDDEN || errorCode == HttpURLConnection.HTTP_UNAUTHORIZED ){

            return getString(R.string.error_unauthorized);

        }

        return getString(R.string.error_unknown);
     }


    protected boolean isError(int errorCode)
    {
        return (errorCode<200 || errorCode >=300);
    }
    View view;
    /**
     * init layout
     */
    private void initLayout() {
        if (view != null) {
            ViewGroup parent = (ViewGroup) view.getParent();
            if (parent != null)
                parent.removeView(view);
        }
        view = View.inflate(context, getLayout(), null);
        // myToolbar = (Toolbar) v.findViewById(R.id.my_toolbar);
        //ViewGroup rl_title = (ViewGroup) v.findViewById(R.id.rl_title);// Layout of title
        // TextView tv_title = (TextView) v.findViewById(R.id.tv_title);// Title
        // ImageButton ib_back = (ImageButton) v.findViewById(R.id.ib_back);// Icon on the Left of title
        // ImageButton ib_right = (ImageButton) v.findViewById(R.id.ib_right);// Icon on the right of title
        // View shadow = v.findViewById(R.id.shadow);// Title shadow
        //  initTitleBar(rl_title, tv_title, ib_back, ib_right, shadow);
        if (view != null) {
            setContentView(view);
       //     view = View.inflate(context, getLayout(), null);

        }
    }

    /**
     * Status Bar
     */
    @TargetApi(Build.VERSION_CODES.KITKAT)
    private void initStatusBar() {
        int color = getColor();
        SystemBarTintManager tintManager = new SystemBarTintManager(this);
        setStatusBarColor(tintManager, color);
    }

    protected void setStatusBarColor(SystemBarTintManager tintManager, int color) {
        tintManager.setStatusBarTintEnabled(true);
        tintManager.setStatusBarTintColor(getResources().getColor(color));
    }

    public int getColor() {
        return R.color.colorPrimary;
    }
    protected abstract int getLayout();

    public void goToOtherActivity(Class a, List data, boolean finishCurrent){
        ActivityBuilder.getBuilder()
                .withContext(context)
                .withClass(a)
                .withParam(data)
                .withFinishCurrent(finishCurrent)
                .build();
    }

}
