package com.android.timesheet.shared;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import com.android.common.AppConfig;
import com.android.timesheet.App;
import com.android.timesheet.BuildConfig;
import com.android.timesheet.shared.interceptors.SignedInterceptor;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Modifier;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * Created by vamsikonanki on 18/8/17.
 */
public class RestClient {

    private static RestClient instance = new RestClient();

    private OkHttpClient.Builder clientBuilder;

    private Retrofit.Builder retrofitBuilder;

    private RestClient() {
        clientBuilder = new OkHttpClient.Builder()
//                .proxy(new Proxy(Proxy.Type.HTTP, new InetSocketAddress("192.168.1.103", 9396)))
                .connectTimeout(360, TimeUnit.SECONDS)
                .writeTimeout(360, TimeUnit.SECONDS)
                .followRedirects(true);

        retrofitBuilder = createRetrofitBuilder();
    }


    Retrofit.Builder createRetrofitBuilder() {
        Gson gson = new GsonBuilder().registerTypeAdapter(Date.class,
                new DateDeserializer()).excludeFieldsWithModifiers(Modifier.FINAL, Modifier.TRANSIENT,
                Modifier.STATIC).create();

        return new Retrofit.Builder().baseUrl(AppConfig.API_BASE_URL).addCallAdapterFactory(RxJavaCallAdapterFactory.create()).addConverterFactory(GsonConverterFactory.create(gson));
    }

    public <T> T convert(ResponseBody body, Class<T> tClass) throws IOException {
        Converter<ResponseBody, T> converter = createAdapter().responseBodyConverter(tClass, new Annotation[0]);
        return converter.convert(body);
    }

    public Retrofit createAdapter() {

        if (BuildConfig.DEBUG) {
            Log.d("SI: ==>", " Mode : DEBUG");
        }

        clientBuilder.interceptors().clear();

        SignedInterceptor interceptor = new SignedInterceptor();
        OkHttpClient client = clientBuilder.addInterceptor(interceptor).build();

        return retrofitBuilder.client(client).build();
    }

    protected SharedPreferences sharedPrefs() {
        return PreferenceManager.getDefaultSharedPreferences(App.getAppContext());
    }

    public void reset() {
        clientBuilder = null;
        retrofitBuilder = null;
        instance = new RestClient();
    }

    public static RestClient getInstance() {
        return instance;
    }

    public static <T> T createService(Class<T> cls) {
        return getInstance().createAdapter().create(cls);
    }

}
