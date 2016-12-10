package id.sch.smktelkom_mlg.learn.yourdailynews;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.orm.SugarContext;

import java.util.ArrayList;

import id.sch.smktelkom_mlg.learn.yourdailynews.models.News;
import id.sch.smktelkom_mlg.learn.yourdailynews.models.NewsDB;
import id.sch.smktelkom_mlg.learn.yourdailynews.services.INews;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements RecyclerViewAdapter.IRecyclerViewAdapter {
    public static final String BASE_URL = "https://newsapi.org/";
    ArrayList<String> authorBerita = new ArrayList<>();
    ArrayList<String> judulBerita = new ArrayList<>();
    ArrayList<String> isiBerita = new ArrayList<>();
    ArrayList<String> gambarBerita = new ArrayList<>();
    ArrayList<String> linkBerita = new ArrayList<>();
    ArrayList<String> tglBerita = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Gson gson = new GsonBuilder()
                .setDateFormat("dd-MM-yyyy")
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        INews apiService =
                retrofit.create(INews.class);

        String newssource = "bbc-news";
        String apiKey = "10ea3dff95124a289bd1ba68c6ca0bad";
        //String sortBy = "latest";

        //Call<News> call = apiService.getSource(newssource, sortBy, apiKey);
        Call<News> call = apiService.getSource(newssource, apiKey);
        call.enqueue(new Callback<News>() {
            @Override
            public void onResponse(Call<News> call, Response<News> response) {
                SugarContext.init(MainActivity.this);
                int statusCode = response.code();
                News news = response.body();
                Log.d("Isi data", news.getArticles().toString());
                ArrayList<News.Article> arrayList = response.body().getArticles();
                if (arrayList != null) {
                    for (int i = 0; i < arrayList.size(); i++) {
                        // Log.d("Isi berita", arrayList.get(i).getTitle());
                        judulBerita.add(arrayList.get(i).getTitle());
                        authorBerita.add(arrayList.get(i).getAuthor());
                        isiBerita.add(arrayList.get(i).getDescription());
                        linkBerita.add(arrayList.get(i).getUrl());
                        gambarBerita.add(arrayList.get(i).getUrlToImage());
                        tglBerita.add(arrayList.get(i).getPublishedAt());
                        NewsDB newsdb = new NewsDB(arrayList.get(i).getTitle().toString(), arrayList.get(i).getDescription().toString(), arrayList.get(i).getUrl().toString());
                        newsdb.save();
                    }
                    RecyclerViewAdapter adapter = new RecyclerViewAdapter(judulBerita, tglBerita, isiBerita, gambarBerita, MainActivity.this);
                    RecyclerView myView = (RecyclerView) findViewById(R.id.recyclerview);
                    myView.setAdapter(adapter);
                    LinearLayoutManager llm = new LinearLayoutManager(MainActivity.this);
                    llm.setOrientation(LinearLayoutManager.VERTICAL);
                    myView.setLayoutManager(llm);
                }
            }

            @Override
            public void onFailure(Call<News> call, Throwable t) {
                // Log error here since request failed
                Log.d("Kesalahan : ", "Gagal ambil data");
            }
        });

        SugarContext.terminate();
    }

    @Override
    public void doClick(int pos) {
        Intent intent = new Intent(MainActivity.this, Detail.class);
        Bundle extras = new Bundle();
        extras.putString("link", linkBerita.get(pos));
        intent.putExtras(extras);
        startActivity(intent);
    }
}
