package com.whatscover.android.activity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.CardView;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.ekalips.fancybuttonproj.FancyButton;
import com.whatscover.android.R;
import com.whatscover.android.utility.A;
import com.whatscover.android.utility.T;
import com.whatscover.android.webservice.LoginInterface;
import com.whatscover.android.webservice.authentication.ServiceGenerator;
import com.whatscover.android.webservice.dto.OAuthRequest;
import com.whatscover.android.webservice.dto.OAuthResponse;
import com.whatscover.android.webservice.dto.UserTest;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.whatscover.android.R.id.et_username;

public class LoginActivity2 extends BaseActivity {

    @BindView(et_username)
    EditText etUsername;
    @BindView(R.id.et_password)
    EditText etPassword;
    @BindView(R.id.bt_go)
    FancyButton btGo;
    @BindView(R.id.cv)
    CardView cv;
    @BindView(R.id.fab)
    FloatingActionButton fab;


    boolean inProgress=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);

        registerValidation();

    }

    @Override
    protected int getLayout() {
        return R.layout.activity_login2;
    }

    @OnClick({R.id.bt_go, R.id.fab})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.fab:
                getWindow().setExitTransition(null);
                getWindow().setEnterTransition(null);

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    ActivityOptions options =
                            ActivityOptions.makeSceneTransitionAnimation(this, fab, fab.getTransitionName());
                    startActivity(new Intent(this, RegisterActivity2.class), options.toBundle());
                } else {
                    A.goOtherActivity(context, RegisterActivity2.class);
                }
                break;
            case R.id.bt_go:
                //goToMainActivity();
                loginAction();
                break;
        }
    }

    @OnClick(R.id.btnFaceBook)
    public void onClickFaceBook() {
        goToOtherActivity(MainActivity.class, null, false);
    }

    private void loginAction(){
        if (mAwesomeValidation.validate()) {
            btGo.collapse();
            inProgress=true;

            final OAuthRequest request = new OAuthRequest(etUsername.getText().toString(), etPassword.getText().toString(), true);
            LoginInterface loginInterface =
                    ServiceGenerator.createService(LoginInterface.class, etUsername.getText().toString(), etPassword.getText().toString());
//                ServiceGenerator.createService(LoginInterface.class, "user", "secretpassword");

            Call<OAuthResponse> call = loginInterface.getAccessToken(request);
            call.enqueue(new Callback<OAuthResponse>() {
                @Override
                public void onResponse(Call<OAuthResponse> call, Response<OAuthResponse> response) {
                    btGo.expand();
                    inProgress=false;

                    if( !isError(response.code()) || response.isSuccessful()) {

                        T.showSuccess(context, R.string.success_message_login);

                        Toast.makeText(getApplicationContext(), response.body().getIdToken(),
                                Toast.LENGTH_SHORT).show();

                        UserTest currentUser = new UserTest();
                        currentUser.setName(etUsername.getText().toString());
                        currentUser.setPassword(etPassword.getText().toString());
                        currentUser.setToken(response.body().getIdToken());
                        userManager.login(currentUser);

                        goToOtherActivity(MainActivity.class, null, false);

                    }else{
                        T.showError(context, parseError( response.code()));
                    }
                }

                @Override
                public void onFailure(Call<OAuthResponse> call, Throwable t) {
                    btGo.expand();
                    inProgress=false;
                    T.showError(context, t.getMessage());
                }
            });
        }else{
            etUsername.setBackground(getResources().getDrawable(R.drawable.selector_bg_edit));
            etPassword.setBackground(getResources().getDrawable(R.drawable.selector_bg_edit));
        }
    }

    private void registerValidation() {

//        mAwesomeValidation.addMailValidation(etUsername);
//        mAwesomeValidation.addPasswordValidation(etPassword);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (!inProgress)
            return super.dispatchTouchEvent(ev);
        return true;
    }
}