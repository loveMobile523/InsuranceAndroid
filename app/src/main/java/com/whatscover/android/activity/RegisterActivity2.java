package com.whatscover.android.activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.CardView;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.animation.AccelerateInterpolator;
import android.widget.EditText;

import com.ekalips.fancybuttonproj.FancyButton;
import com.whatscover.android.R;
import com.whatscover.android.utility.T;
import com.whatscover.android.webservice.RegisterInterface;
import com.whatscover.android.webservice.authentication.ServiceGenerator;
import com.whatscover.android.webservice.dto.RegisterRequest;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by IsaAndi on 11/05/2017.
 */

public class RegisterActivity2 extends BaseActivity {
    private final static String TAG = RegisterActivity.class.getSimpleName();
    @BindView(R.id.fab)
    FloatingActionButton fab;
    @BindView(R.id.cv_add)
    CardView cvAdd;

    @BindView(R.id.et_first_name)
    EditText etFirstName;
    @BindView(R.id.et_last_name)
    EditText etLastName;
    @BindView(R.id.etMail)
    EditText etMail;
    @BindView(R.id.etPassword)
    EditText etPassword;
    @BindView(R.id.bt_go)
    FancyButton btGo;

    boolean inProgress=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            ShowEnterAnimation();
        }
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animateRevealClose();
            }
        });

        registerValidation();
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_register2;
    }

    @OnClick(R.id.bt_go)
    public void onClick(View view) {
        registerAction();
    }

    private void ShowEnterAnimation() {
        Transition transition = TransitionInflater.from(this).inflateTransition(R.transition.fabtransition);
        getWindow().setSharedElementEnterTransition(transition);

        transition.addListener(new Transition.TransitionListener() {
            @Override
            public void onTransitionStart(Transition transition) {
                cvAdd.setVisibility(View.GONE);
            }

            @Override
            public void onTransitionEnd(Transition transition) {
                transition.removeListener(this);
                animateRevealShow();
            }

            @Override
            public void onTransitionCancel(Transition transition) {

            }

            @Override
            public void onTransitionPause(Transition transition) {

            }

            @Override
            public void onTransitionResume(Transition transition) {

            }


        });
    }

    public void animateRevealShow() {
        Animator mAnimator = ViewAnimationUtils.createCircularReveal(cvAdd, cvAdd.getWidth()/2,0, fab.getWidth() / 2, cvAdd.getHeight());
        mAnimator.setDuration(500);
        mAnimator.setInterpolator(new AccelerateInterpolator());
        mAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
            }

            @Override
            public void onAnimationStart(Animator animation) {
                cvAdd.setVisibility(View.VISIBLE);
                super.onAnimationStart(animation);
            }
        });
        mAnimator.start();
    }

    public void animateRevealClose() {
        Animator mAnimator = ViewAnimationUtils.createCircularReveal(cvAdd,cvAdd.getWidth()/2,0, cvAdd.getHeight(), fab.getWidth() / 2);
        mAnimator.setDuration(500);
        mAnimator.setInterpolator(new AccelerateInterpolator());
        mAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                cvAdd.setVisibility(View.INVISIBLE);
                super.onAnimationEnd(animation);
                fab.setImageResource(R.drawable.plus);
                RegisterActivity2.super.onBackPressed();
            }

            @Override
            public void onAnimationStart(Animator animation) {
                super.onAnimationStart(animation);
            }
        });
        mAnimator.start();
    }
    @Override
    public void onBackPressed() {
        animateRevealClose();
    }

    private void registerValidation() {

        mAwesomeValidation.addNameValidation(etFirstName);
        mAwesomeValidation.addNameValidation(etLastName);
        mAwesomeValidation.addMailValidation(etMail);
        mAwesomeValidation.addPasswordValidation(etPassword);
    }

    private void registerAction(){
        if (mAwesomeValidation.validate()) {
            btGo.collapse();
            btGo.setBackgroundColor(getResources().getColor(R.color.colorTextLabelLogin));
            inProgress=true;

            final RegisterRequest request = new RegisterRequest(etFirstName.getText().toString(), etLastName.getText().toString(),
                    etMail.getText().toString(), etPassword.getText().toString(), etMail.getText().toString());
            RegisterInterface registerInterface =
                    ServiceGenerator.createService(RegisterInterface.class, etMail.getText().toString(), etPassword.getText().toString());

            Call<Void> call = registerInterface.registerUser(request);
            call.enqueue(new Callback<Void>() {
                @Override
                public void onResponse(Call<Void> call, Response<Void> response) {
                    btGo.setBackgroundColor(getResources().getColor(R.color.white));
                    btGo.expand();
                    inProgress=false;

                    Log.d(TAG, call + " response code:" +response.code() + "Call :" + call.request());

                    if( !isError(response.code()) || response.isSuccessful()) {

                        T.showSuccess(context,R.string.success_message_register);
                        fab.callOnClick();
                    }else{
                        T.showSuccess(context, parseError( response.code()));
                    }
                }

                @Override
                public void onFailure(Call<Void> call, Throwable t) {
                    btGo.setBackgroundColor(getResources().getColor(R.color.white));
                    btGo.expand();
                    inProgress=false;

                    Log.e(TAG,t.getMessage());
                    T.showError(context, t.getMessage());
                }
            });
        }else{
            etFirstName.setBackground(getResources().getDrawable(R.drawable.selector_bg_edit_2));
            etLastName.setBackground(getResources().getDrawable(R.drawable.selector_bg_edit_2));
            etMail.setBackground(getResources().getDrawable(R.drawable.selector_bg_edit_2));
            etPassword.setBackground(getResources().getDrawable(R.drawable.selector_bg_edit_2));
        }
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (!inProgress)
            return super.dispatchTouchEvent(ev);
        return true;
    }
}
