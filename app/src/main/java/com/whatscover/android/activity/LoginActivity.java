package com.whatscover.android.activity;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.InputType;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

//import com.facebook.CallbackManager;
//import com.facebook.FacebookCallback;
//import com.facebook.FacebookException;
//import com.facebook.FacebookSdk;
//import com.facebook.login.LoginResult;
//import com.facebook.login.widget.LoginButton;
import com.whatscover.android.R;
import com.whatscover.android.webservice.dto.OAuthRequest;
import com.whatscover.android.webservice.dto.OAuthResponse;
import com.whatscover.android.webservice.dto.UserTest;
import com.whatscover.android.utility.A;
import com.whatscover.android.utility.ProgressBar;
import com.whatscover.android.utility.T;
import com.whatscover.android.webservice.LoginInterface;
import com.whatscover.android.webservice.authentication.ServiceGenerator;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends BaseActivity {

    @BindView(R.id.btnLogin)
    Button btnLogin;
    @BindView(R.id.etMail)
    EditText etMail;
    @BindView(R.id.etPassword)
    EditText etPassword;

    @BindView(R.id.imgHide)
    ImageView imgHide;

    @BindString(R.string.blank_email)
    String blankEmail;
    @BindString(R.string.blank_password)
    String blankPassword;

    private boolean judgeHide;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        btnLogin.setPaintFlags(btnLogin.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);

        judgeHide = true;
        showHideImage();
    }

    public void showHideImage() {
        if( judgeHide ) {
            imgHide.setImageResource(R.drawable.ic_hide);
            etPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        } else {
            imgHide.setImageResource(R.drawable.ic_show);
            etPassword.setInputType(InputType.TYPE_CLASS_TEXT);
        }
        etPassword.setText(etPassword.getText().toString());
        etPassword.setSelection(etPassword.getText().length());
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_login;
    }

    @OnClick(R.id.btnLogin)
    public void onClickLogin(Button btnLogin) {

        if(etMail.getText().toString().equals("")) {
            T.showError(context, blankEmail);
            return;
        }

        if(etPassword.getText().toString().equals("")) {
            T.showError(context, getString(R.string.blank_password));
            return;
        }


        final ProgressBar progressBar = ProgressBar.show(context, "Logging In");
        //Call authentication service and save information in preferences using
        //setLogined(user);

        final OAuthRequest request = new OAuthRequest(etMail.getText().toString(), etPassword.getText().toString(), true);
        LoginInterface loginInterface =
                ServiceGenerator.createService(LoginInterface.class, etMail.getText().toString(), etPassword.getText().toString());
//                ServiceGenerator.createService(LoginInterface.class, "user", "secretpassword");

        Call<OAuthResponse> call = loginInterface.getAccessToken(request);
        call.enqueue(new Callback<OAuthResponse>() {
            @Override
            public void onResponse(Call<OAuthResponse> call, Response<OAuthResponse> response) {
                progressBar.dismiss();

                if( !isError(response.code()) || response.isSuccessful()) {

                    T.showSuccess(context, R.string.success_message_login);

                    Toast.makeText(getApplicationContext(), response.body().getIdToken(),
                            Toast.LENGTH_SHORT).show();

                    UserTest currentUser = new UserTest();
                    currentUser.setName(etMail.getText().toString());
                    currentUser.setPassword(etPassword.getText().toString());
                    currentUser.setToken(response.body().getIdToken());
                    userManager.login(currentUser);
                    A.goOtherActivityFinish(context, MainActivity.class);

                }else{
                    T.showSuccess(context, parseError( response.code()));
                }
            }

            @Override
            public void onFailure(Call<OAuthResponse> call, Throwable t) {
                progressBar.dismiss();
                T.showError(context, t.getMessage());
            }
        });
    }

    @OnClick(R.id.imgHide)
    public void onClickHide() {
        judgeHide = !judgeHide;
        showHideImage();
    }

    @OnClick(R.id.btnFaceBook1)
    public void onClickFaceBook() {

    }

    @OnClick(R.id.tvToRegister)
    public void onClickToRegister() {
        A.goOtherActivityFinish(context, RegisterActivity.class);
    }

    private void setLogined(UserTest user) {
        userManager.login(user);
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }
}
