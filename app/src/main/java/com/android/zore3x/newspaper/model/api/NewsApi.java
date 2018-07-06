package com.android.zore3x.newspaper.model.api;

import com.android.zore3x.newspaper.model.Response;
import com.android.zore3x.newspaper.model.api.Endpoints.Everything;
import com.android.zore3x.newspaper.model.api.Endpoints.Sources;
import com.android.zore3x.newspaper.model.api.Endpoints.TopHeadlines;

import io.reactivex.Observable;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class NewsApi {

    public static final String BASE_URL = "https://newsapi.org/v2/";

    private TopHeadlines mTopHeadlinesService;
    private Everything mEverythingService;
    private Sources mSourcesService;

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
        mEverythingService = retrofit.create(Everything.class);
        mSourcesService = retrofit.create(Sources.class);
    }

    public Observable<Response> getTopHeadlinesNews(int page, int pageSize, String county) {
        return mTopHeadlinesService.getTopHeadlines(page, pageSize, county);
    }

    public Observable<Response> getEverything(int page, int pageSize, String q) {
        if(q.isEmpty()) {
            return mEverythingService.getEverything(page, pageSize, "world news", null, null, null, null, null, null);
        } else {
            return mEverythingService.getEverything(page, pageSize, q, null, null, null, null, null, null);
        }
    }

    public Observable<Response> getEverything(int page, int pageSize, String q, SortBy sortBy) {
        if(q.isEmpty()) {
            return mEverythingService.getEverything(page, pageSize, "world news", null, null, null, null, null, sortBy.getSortBy());
        } else {
            return mEverythingService.getEverything(page, pageSize, q, null, null, null, null, null, sortBy.getSortBy());
        }
    }

    public Observable<Response> getEverything(int page, int pageSize, String q, String from, String to) {
        if(q.isEmpty()) {
            return mEverythingService.getEverything(page, pageSize, "world news", null, null, from, to, null, null);
        } else {
            return mEverythingService.getEverything(page, pageSize, q, null, null, null, null, null, null);
        }
    }

    public Observable<ResponseSource> getSources() {
        return mSourcesService.getSources();
    }

}
