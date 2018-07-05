package com.android.zore3x.newspaper.model.api;

import com.android.zore3x.newspaper.model.Response;
import com.android.zore3x.newspaper.model.api.Endpoints.TopHeadlines;

import io.reactivex.Observable;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class NewsApi {

    public static final String BASE_URL = "https://newsapi.org/v2/";

    private TopHeadlines mTopHeadlinesService;

    public NewsApi(String apiKey) {

        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(BASE_URL)
                .client(new OkHttpClient.Builder()
                        .addInterceptor(new HeaderInterceptor(apiKey))
                        .build())
                .build();

        mTopHeadlinesService = retrofit.create(TopHeadlines.class);
    }

    public Observable<Response> getTopHeadlinesNews(int page, int pageSize, String county) {
        return mTopHeadlinesService.getTopHeadlines(page, pageSize, county);
    }

}
