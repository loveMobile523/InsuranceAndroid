package com.whatscover.android.webservice.authentication.other;

import android.content.Context;

import com.whatscover.android.webservice.dto.AddSuggestionResponse;
import com.whatscover.android.webservice.dto.UserTest;
import com.whatscover.android.utility.T;
import com.whatscover.android.webservice.SuggestionInterface;
import com.whatscover.android.webservice.authentication.ServiceGenerator;
import com.whatscover.android.webservice.authentication.UserManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

import okhttp3.Authenticator;
import okhttp3.Credentials;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Route;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by dorin on 02.05.2017.
 */

public class OAuthClientUtil {
    private static final String CLIENT_ID = "";
    private static final String API_ENDPOINT = "http://192.168.0.11:8080/";
    private static final String CLIENT_SECRET = "";

    private Retrofit retrofit;
    private Retrofit retrofitAuthenticated;
    private String accessToken = "";
    private static boolean tokenExpired = true;
//    private OAuthRequest oAuthRequest;

    private UserManager userManager;
    protected Context context;
    private static Logger logger = LoggerFactory.getLogger(OAuthClientUtil.class);


    // ---------------------------------------------------------------------------------------------
//    private static OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
//
//    private static Retrofit.Builder builder =
//            new Retrofit.Builder()
//                    .baseUrl(API_ENDPOINT)
//                    .addConverterFactory(GsonConverterFactory.create());
//
//    private static Retrofit retrofit = builder.build();

    // ---------------------------------------------------------------------------------------------
    public OAuthClientUtil(final UserManager userManager, Context context) {
        this.userManager = userManager;
        this.context = context;
        initializeRetrofitClients();
    }

    /**
     * This method initializes the retrofit clients
     * a) One for the initial authentication end point
     * b) Other for other service requests
     */
    private void initializeRetrofitClients() {
        OkHttpClient.Builder builder = new OkHttpClient().newBuilder();
        OkHttpClient clientNormal;
        OkHttpClient clientAuthenticated;
        builder.interceptors().add(new Interceptor() {

            @Override
            public okhttp3.Response intercept(Chain chain) throws IOException {
                Request originalRequest = chain.request();
                Request.Builder builder = originalRequest.newBuilder().header("Authorization:Bearer ", accessToken).
                        method(originalRequest.method(), originalRequest.body());
                okhttp3.Response response = chain.proceed(builder.build());
                /*
                implies that the token has expired
                or was never initialized
                 */
                if (response.code() == 401) {
                    tokenExpired = true;
                    logger.info("Token Expired");
                    getAuthenticationToken();
                    builder = originalRequest.newBuilder().header("Authorization:Bearer ", accessToken).
                            method(originalRequest.method(), originalRequest.body());
                    response = chain.proceed(builder.build());
                }
                return response;
            }
        });

        clientAuthenticated = builder.build();
        retrofitAuthenticated = new Retrofit.Builder().client(clientAuthenticated)
                .baseUrl(API_ENDPOINT)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        OkHttpClient.Builder builder1 = new OkHttpClient().newBuilder();
        builder1.authenticator(new Authenticator() {
            @Override
            public Request authenticate(Route route, okhttp3.Response response) throws IOException {
                String authentication = Credentials.basic(CLIENT_ID, CLIENT_SECRET);
                Request.Builder builder = response.request().newBuilder().addHeader("Authorization", authentication);
                return builder.build();
            }
        });
        clientNormal = builder1.build();
        retrofit = new Retrofit.Builder().client(clientNormal).
                baseUrl(API_ENDPOINT).
                addConverterFactory(GsonConverterFactory.create()).build();
    }

    /**
     * Is invoked only when the access token is required
     * Or it expires
     */
    private void getAuthenticationToken() {
//        OAuthInterface oAuthInterface = this.retrofit.create(OAuthInterface.class);
//        Call<OAuthResponse> authRequestCall = oAuthInterface.getAccessToken(oAuthRequest);
//        Response<OAuthResponse> response = null;
//        try {
//            response = authRequestCall.execute();
//            if (response.isSuccessful()) {
//                accessToken = response.body().getIdToken();
//            }
//        } catch (IOException e) {
//            logger.error("Exception occurred due to ", e);
//        } finally {
//
//        }


        UserTest userTest = userManager.read();

        SuggestionInterface suggestionInterface =
                ServiceGenerator.createService(SuggestionInterface.class, userTest.getName(), userTest.getPassword());
        Call<AddSuggestionResponse> call = suggestionInterface.addSuggestion();
        call.enqueue(new Callback<AddSuggestionResponse>() {
            @Override
            public void onResponse(Call<AddSuggestionResponse> call, Response<AddSuggestionResponse> response) {
//                Toast.makeText(getApplicationContext(), "Success", Toast.LENGTH_SHORT).show();

                if(response.message().equals("Unauthorized")) {
                    T.showError(context, response.message());
                }

//                AddSuggestionResponse addSuggestionResponse = response.body();
//                int i = 0;
            }

            @Override
            public void onFailure(Call<AddSuggestionResponse> call, Throwable t) {
                T.showError(context, t.getMessage());
            }
        });

    }

}
