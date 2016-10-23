package com.cspack.nytsearch.network;

import com.cspack.nytsearch.models.FilterConfig;
import com.cspack.nytsearch.models.articlesearch.ArticleSearch;
import com.cspack.nytsearch.models.articlesearch.Byline;
import com.cspack.nytsearch.models.articlesearch.BylineDeserializer;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

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

    private static String buildFilterQuery(List<String> newsDeskFilters) {
        if (newsDeskFilters == null || newsDeskFilters.size() == 0) {
            return null;
        }
        StringBuilder concatFilters = new StringBuilder();
        for (String filter : newsDeskFilters) {
            concatFilters.append("\"");
            concatFilters.append(filter);
            concatFilters.append("\" ");
        }
        return String.format("news_desk:(%s)", concatFilters.toString());
    }

    private static String parseDateMicro(long micro) {
        if (micro <= 0) {
            return null;
        }
        final SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
        return formatter.format(new Date(micro));
    }

    public static void MakeArticleSearch(FilterConfig config, Callback<ArticleSearch> callback) {
        Gson gson = new GsonBuilder().registerTypeAdapter(
                Byline.class, new BylineDeserializer()).create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(NYT_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        ArticleSearchInterface searchInterface = retrofit.create(ArticleSearchInterface.class);
        Call<ArticleSearch> search = searchInterface.articleSearch(
                API_KEY, config.getQuery(), buildFilterQuery(config.getNewsDeskFilters()),
                config.getSortOrder(), parseDateMicro(config.getBeginDateMicros()),
                parseDateMicro(config.getEndDateMicros()), config.getPage());
        search.enqueue(callback);
    }
}
