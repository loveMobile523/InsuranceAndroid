package com.whatscover.android.webservice.authentication;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by dorin on 30.04.2017.
 */

public class AuthorizationInterceptor implements Interceptor {
    private String authToken;

    public AuthorizationInterceptor(String token) {
        this.authToken = token;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request original = chain.request();

        Request.Builder builder = original.newBuilder()
                .header("Authorization", " Bearer " + authToken);

        Request request = builder.build();
        return chain.proceed(request);
    }
}
