package siddhu.test.com.myfregment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
 * Created by admin on 8/6/2016.
 * List of Articles--ViewPager--Fragment--Recycle--Multiple Elements
 */

public class ListOfArticlesFragment extends Fragment {

    private static final String KEY_POSITION = "position";
    private static final int DEFAULT_POSITION = 0;

    RecyclerView recyclerView;
    ProgressBar progressBar;

    Source mySource;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int position = getArguments().getInt(KEY_POSITION,DEFAULT_POSITION);
        mySource = CommonUsage.getAllNewSource().get(position);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(container.getContext()).inflate(R.layout.activity_main,container,false);
        //return super.onCreateView(inflater, container, savedInstanceState);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = (RecyclerView) view.findViewById(R.id.activity_main_newsitems);
        progressBar = (ProgressBar) view.findViewById(R.id.activity_main_progress);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        Call<NewsApiArticleResponse> responseCall = NewsAPI.getNewsAPI().getArticles(mySource.getId(), mySource.getSortBysAvailable().get(0));
        responseCall.enqueue(new Callback<NewsApiArticleResponse>() {
            @Override
            public void onResponse(Call<NewsApiArticleResponse> call, Response<NewsApiArticleResponse> response) {
                progressBar.setVisibility(View.GONE);
                NewsApiArticleResponse newsApiArticleResponse = response.body();
                List<Article> articles = newsApiArticleResponse.getArticles();


                CommonUsage.setAllNewArticle(articles);

                NewAdapter newAdapter = new NewAdapter(CommonUsage.getAllNewArticle());
                recyclerView.setAdapter(newAdapter);

                //Toast.makeText(getActivity(), "Fragment article response received", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<NewsApiArticleResponse> call, Throwable t) {
                Toast.makeText(getActivity(), "Fragement article error received", Toast.LENGTH_SHORT).show();
                progressBar.setVisibility(View.GONE);
            }
        });


    }

    public static ListOfArticlesFragment generateFragment(int position)
    {
        ListOfArticlesFragment listOfArticlesFragment = new ListOfArticlesFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(KEY_POSITION, position);
        listOfArticlesFragment.setArguments(bundle);
        return listOfArticlesFragment;
    }
}
