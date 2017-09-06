package com.android.timesheet.shared.interceptors;

import android.util.Log;

import java.io.IOException;
import java.util.Date;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;


/**
 * Created by vamsikonanki on 3/4/16.
 */
public class SignedInterceptor implements Interceptor {

    public SignedInterceptor() {

    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request.Builder requestBuilder = chain.request()
                .newBuilder()
                .addHeader("Content-Type", "application/json");

        Request request = requestBuilder.build();

        Log.i("Request:@-"+ new Date().getTime(), request.url().toString());
        Log.i("Request:@-"+ new Date().getTime(), request.headers().toString());

        return chain.proceed(request);

    }
}
