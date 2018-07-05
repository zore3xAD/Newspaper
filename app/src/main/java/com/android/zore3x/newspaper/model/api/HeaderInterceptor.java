package com.android.zore3x.newspaper.model.api;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Response;

public class HeaderInterceptor implements Interceptor {

    private String mApiKey;

    public HeaderInterceptor(String apiKey) {
        mApiKey = apiKey;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        return chain.proceed(chain.request().newBuilder()
                .addHeader("Authorization", "Bearer " + mApiKey)
                .build());
    }
}
