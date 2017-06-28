package com.whatscover.android.activity;

import android.graphics.Paint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import com.whatscover.android.R;
import com.whatscover.android.webservice.dto.RegisterRequest;
import com.whatscover.android.webservice.dto.UserTest;
import com.whatscover.android.utility.ProgressBar;
import com.whatscover.android.utility.T;
import com.whatscover.android.webservice.RegisterInterface;
import com.whatscover.android.webservice.authentication.ServiceGenerator;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends BaseActivity {
private final static String TAG = RegisterActivity.class.getSimpleName();
    @BindView(R.id.btnRegister)
    Button btnRegister;
    @BindView(R.id.etFirstName)
    EditText etFirstName;
    @BindView(R.id.etLastName)
    EditText etLastName;
    @BindView(R.id.etMail)
    EditText etMail;
    @BindView(R.id.etPassword)
    EditText etPassword;
    @BindView(R.id.etConfirmPassword)
    EditText etConfirmPassword;

    @BindString(R.string.blank_first_name)
    String blankFirstName;
    @BindString(R.string.blank_last_name)
    String blankLastName;
    @BindString(R.string.blank_email)
    String blankEmail;
    @BindString(R.string.blank_password)
    String blankPassword;
    @BindString(R.string.blank_confirm_password)
    String blankConfirmPassword;
    @BindString(R.string.check_password)
    String checkPassword;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        btnRegister.setPaintFlags(btnRegister.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_register;
    }

    @OnClick(R.id.btnRegister)
    public void onClickRegister(Button btnRegister) {

        String firstName = etFirstName.getText().toString();
        String lastName = etLastName.getText().toString();
        String email = etMail.getText().toString();
        String password = etPassword.getText().toString();
        String confirmPassword = etConfirmPassword.getText().toString();

        if(firstName.equals("")) {
            T.showError(context, blankFirstName);
            return;
        }

        if(lastName.equals("")) {
            T.showError(context, blankLastName);
            return;
        }

        if(email.equals("")) {
            T.showError(context, blankEmail);
            return;
        }

        if(password.equals("")) {
            T.showError(context, blankPassword);
            return;
        }

        if(confirmPassword.equals("")) {
            T.showError(context, blankConfirmPassword);
            return;
        }

        if(!confirmPassword.equals(password)) {
            T.showError(context, checkPassword);
            return;
        }

        // -----------------------------------------------------------------------------------------
        final ProgressBar progressBar = ProgressBar.show(context, "Register ...");


        final RegisterRequest request = new RegisterRequest(firstName, lastName, email, password, email);
        RegisterInterface registerInterface =
                ServiceGenerator.createService(RegisterInterface.class, email, password);

        Call<Void> call = registerInterface.registerUser(request);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                progressBar.dismiss();

                Log.d(TAG, call + " response code:" +response.code() + "Call :" + call.request());

                if( !isError(response.code()) || response.isSuccessful()) {

                    T.showSuccess(context,R.string.success_message_register);

                }else{
                    T.showSuccess(context, parseError( response.code()));
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                progressBar.dismiss();

                Log.e(TAG,t.getMessage());
                T.showError(context, t.getMessage());
            }
        });
    }



    private void setLogined(UserTest user) {
        userManager.login(user);
    }


}
