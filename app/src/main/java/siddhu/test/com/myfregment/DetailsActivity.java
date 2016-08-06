package siddhu.test.com.myfregment;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import siddhu.test.com.myfregment.objects.Article;
import siddhu.test.com.myfregment.objects.CommonUsage;

/**
 * Created by admin on 8/4/2016.
 */

public class DetailsActivity extends AppCompatActivity {

    WebView webView;
    ProgressBar progressBar;
    static Context globalContext;

    private static final int DEFAULT_POSITION=-1;
    private static final String KEY_POSITION="position";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.news_detail);
        progressBar = (ProgressBar) findViewById(R.id.news_details_progressbar);
        webView = (WebView) findViewById(R.id.activity_details_webview);
        int position = getIntent().getIntExtra(KEY_POSITION,DEFAULT_POSITION);
        Article newsObjects = CommonUsage.getAllNewArticle().get(position);
        getSupportActionBar().setTitle(newsObjects.getTitle());
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
        webView.loadUrl(newsObjects.getUrl());
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
        globalContext = context;
        Intent intent = new Intent(context, DetailsActivity.class);
        intent.putExtra("position",position);
        context.startActivity(intent);
    }

}
