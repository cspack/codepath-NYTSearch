package com.cspack.nytsearch.network;

import android.util.Log;

import com.cspack.nytsearch.models.articlesearch.ArticleSearch;
import com.cspack.nytsearch.models.articlesearch.Byline;
import com.cspack.nytsearch.models.articlesearch.BylineDeserializer;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by chris on 10/18/16.
 */

public class NewYorkTimesClient {
    private static final String API_KEY = "3efd99e4fcb448978e05f7b9acd02e0a";
    private static final String NYT_BASE_URL = "https://api.nytimes.com/";

    public interface ArticleSearchInterface {
        @GET("/svc/search/v2/articlesearch.json")
        Call<ArticleSearch> articleSearch(
                // q = query term
                // begin_date = YYYYMMDD string
                // end_date = YYYYMMDD string
                // sort = 'newest', 'oldest'
                // fl = field limiting
                // page = page number
                // fq = news_desk:("Sports" "Energy")
                @Query("api-key") String apiKey,
                @Query("q") String query,
                @Query("fq") String filterQuery,
                @Query("sort") String sortOrder,
                @Query("begin_date") String beginDate,
                @Query("end_date") String endDate,
                @Query("page") Integer page);
    }

    public static void MakeArticleSearch(Callback<ArticleSearch> callback) {
        Gson gson = new GsonBuilder().registerTypeAdapter(
                Byline.class, new BylineDeserializer()).create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(NYT_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        ArticleSearchInterface searchInterface = retrofit.create(ArticleSearchInterface.class);
        Call<ArticleSearch> search = searchInterface.articleSearch(
                API_KEY, "android", null, "newest", null, null, 0);
        search.enqueue(callback);
    }
}
