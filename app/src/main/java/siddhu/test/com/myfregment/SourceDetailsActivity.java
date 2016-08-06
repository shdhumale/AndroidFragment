package siddhu.test.com.myfregment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import siddhu.test.com.myfregment.network.NewsAPI;
import siddhu.test.com.myfregment.objects.Article;
import siddhu.test.com.myfregment.objects.CommonUsage;
import siddhu.test.com.myfregment.objects.NewsApiArticleResponse;
import siddhu.test.com.myfregment.objects.Source;

/**
 * Created by admin on 8/4/2016.
 */

public class SourceDetailsActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ProgressBar progressBar;

    private static final int DEFAULT_POSITION=-1;
    private static final String KEY_POSITION="position";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.news_detail);
        setContentView(R.layout.item_activity);


       /* progressBar = (ProgressBar) findViewById(R.id.news_details_progressbar);
        webView = (WebView) findViewById(R.id.activity_details_webview);
        int position = getIntent().getIntExtra(KEY_POSITION,DEFAULT_POSITION);
        Source newsObjects = CommonUsage.getAllNewSource().get(position);
        getSupportActionBar().setTitle(newsObjects.getName());
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                progressBar.setVisibility(View.VISIBLE);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                progressBar.setVisibility(View.INVISIBLE);
            }
        });
        webView.loadUrl(newsObjects.getUrl());*/

        recyclerView = (RecyclerView) findViewById(R.id.activity_main_newsitems);
        progressBar = (ProgressBar) findViewById(R.id.activity_main_progress);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        progressBar.setVisibility(View.VISIBLE);
        int position = getIntent().getIntExtra(KEY_POSITION,DEFAULT_POSITION);
        Source source = CommonUsage.getAllNewSource().get(position);
        getSupportActionBar().setTitle(source.getName());
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Call<NewsApiArticleResponse> responseCall = NewsAPI.getNewsAPI().getArticles(source.getId(), source.getSortBysAvailable().get(0));
        responseCall.enqueue(new Callback<NewsApiArticleResponse>() {
            @Override
            public void onResponse(Call<NewsApiArticleResponse> call, Response<NewsApiArticleResponse> response) {
                progressBar.setVisibility(View.GONE);
                NewsApiArticleResponse newsApiArticleResponse = response.body();
                List<Article> articles = newsApiArticleResponse.getArticles();


                CommonUsage.setAllNewArticle(articles);

                NewAdapter newAdapter = new NewAdapter(CommonUsage.getAllNewArticle());
                recyclerView.setAdapter(newAdapter);

                Toast.makeText(SourceDetailsActivity.this, "article response received", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<NewsApiArticleResponse> call, Throwable t) {
                Toast.makeText(SourceDetailsActivity.this, "article error received", Toast.LENGTH_SHORT).show();
            }
        });


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
       /* switch (item.getItemId()) {
            case android.R.id.home
                return true;
            default:
            return super.onOptionsItemSelected(item);
        }*/
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
       /* int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.activity_details_webview) {
            Intent intent = new Intent(globalContext, MainActivity.class);
            globalContext.startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);*/
        switch (item.getItemId()) {
            case android.R.id.home:
                //onBackPressed();
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    public static void start (Context context, int position)
    {
        Intent intent = new Intent(context, SourceDetailsActivity.class);
        intent.putExtra("position",position);
        context.startActivity(intent);
    }

}
