package id.sch.smktelkom_mlg.learn.yourdailynews.services;

import id.sch.smktelkom_mlg.learn.yourdailynews.models.News;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Administrator on 10/12/2016.
 */

public interface INews {

    @GET("v1/articles")
        //Call<News> getSource(@Query("source") String source, @Query("sortBy") String sortBy, @Query("apiKey") String apiKey);
    Call<News> getSource(@Query("source") String source, @Query("apiKey") String apiKey);

    Call<News> getArticles();
}
