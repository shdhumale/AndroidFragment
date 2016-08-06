package siddhu.test.com.myfregment.network;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;
import siddhu.test.com.myfregment.objects.NewsApiArticleResponse;
import siddhu.test.com.myfregment.objects.NewsApiSourcesResponse;

/**
 * Created by admin on 8/5/2016.
 */

public class NewsAPI {

    private static final String API_KEY = "a240d18a16554d7e89f79f5c904910e0";
    private static final String BASE_URL = "https://newsapi.org/v1/";

    private static NewsAPIInterface newsAPIInterface;

    public static NewsAPIInterface getNewsAPI() {
        if (newsAPIInterface == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            newsAPIInterface = retrofit.create(NewsAPIInterface.class);
        }
        return newsAPIInterface;
    }

    public interface NewsAPIInterface {
        @GET("articles?apiKey=" + API_KEY)
        Call<NewsApiArticleResponse> getArticles(@Query("source") String source, @Query("sortBy") String sortBy);

        @GET("sources")
        Call<NewsApiSourcesResponse> getSources();
    }
}

