package com.lexing.learndagger.adapter;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.lexing.learndagger.Constants;
import com.lexing.learndagger.api.GitHubApi;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.logging.HttpLoggingInterceptor;

import java.util.concurrent.TimeUnit;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;

/**
 * Author: mopel(amosbake@outlook.com)
 * Date : 2016/2/4
 * TIME : 11:22
 */
public class RestApiAdapter {
    public static final String API_BASE_URL = Constants.BASE_URL;
    private static OkHttpClient httpClient = new OkHttpClient();

    static {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        httpClient.setConnectTimeout(Constants.CONNECT_TIME_OUT, TimeUnit.SECONDS);
        httpClient.interceptors().add(loggingInterceptor);
    }

    //gson 解析格式
    private static Gson gson = new GsonBuilder()
            .create();

    private static Retrofit.Builder builder =
            new Retrofit.Builder()
                    .baseUrl(API_BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson));

    private static <S> S createService(Class<S> serviceClass) {
        Retrofit retrofit = builder.client(httpClient).build();
        return retrofit.create(serviceClass);
    }

    private static class Holder {
        static final GitHubApi INSTANCE = createService(GitHubApi.class);
    }

    public static GitHubApi getClient() {
        return Holder.INSTANCE;
    }
}
