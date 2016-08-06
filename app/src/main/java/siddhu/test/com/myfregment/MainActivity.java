package siddhu.test.com.myfregment;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import siddhu.test.com.myfregment.network.NewsAPI;
import siddhu.test.com.myfregment.objects.CommonUsage;
import siddhu.test.com.myfregment.objects.NewsApiSourcesResponse;
import siddhu.test.com.myfregment.objects.Source;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ProgressBar progressBar;
    //List<NewsObjects> newsItems = new ArrayList<NewsObjects>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.activity_main_newsitems);
        progressBar = (ProgressBar) findViewById(R.id.activity_main_progress);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        progressBar.setVisibility(View.VISIBLE);

/*
        Call<NewsApiArticleResponse> responseCall = NewsAPI.getNewsAPI().getArticles("bbcnews", "top");
        responseCall.enqueue(new Callback<NewsApiArticleResponse>() {
            @Override
            public void onResponse(Call<NewsApiArticleResponse> call, Response<NewsApiArticleResponse> response) {
                progressBar.setVisibility(View.GONE);
                NewsApiArticleResponse newsApiArticleResponse = response.body();
                List<Article> articles = newsApiArticleResponse.getArticles();
               */
/* for(int i=0;i<articles.size();i++)
                {
                    //String imageUrl, String title, String date, String description, String detailsUrl
                    NewsObjects newsObjects = new NewsObjects(articles.get(i).getUrlToImage(),articles.get(i).getTitle(),articles.get(i).getPublishedAt(),articles.get(i).getDescription(),articles.get(i).getUrl());
                    newsItems.add(newsObjects);
                }*//*

                CommonUsage.setAllNewArticle(articles);

                NewAdapter newAdapter = new NewAdapter(CommonUsage.getAllNewArticle());
                recyclerView.setAdapter(newAdapter);

                Toast.makeText(MainActivity.this, "response received", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<NewsApiArticleResponse> call, Throwable t) {
                Toast.makeText(MainActivity.this, "error received", Toast.LENGTH_SHORT).show();
            }
        });
*/

        Call<NewsApiSourcesResponse> responseCall = NewsAPI.getNewsAPI().getSources();
        responseCall.enqueue(new Callback<NewsApiSourcesResponse>() {
            @Override
            public void onResponse(Call<NewsApiSourcesResponse> call, Response<NewsApiSourcesResponse> response) {
                progressBar.setVisibility(View.GONE);
                NewsApiSourcesResponse newsApiArticleResponse = response.body();
                List<Source> sources = newsApiArticleResponse.getSources();


                CommonUsage.setAllNewSource(sources);

                NewSourceAdapter newSourceAdapter = new NewSourceAdapter(CommonUsage.getAllNewSource());
                recyclerView.setAdapter(newSourceAdapter);

                Toast.makeText(MainActivity.this, "Source response received", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<NewsApiSourcesResponse> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Source  error received", Toast.LENGTH_SHORT).show();
            }
        });



    }
}
