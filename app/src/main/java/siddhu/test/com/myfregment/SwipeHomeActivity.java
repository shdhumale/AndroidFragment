package siddhu.test.com.myfregment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
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

/**
 * Created by admin on 8/6/2016.
 */

public class SwipeHomeActivity extends AppCompatActivity {

    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swipe_home);
        viewPager = (ViewPager) findViewById(R.id.activity_swipe_view_pager);
        Call<NewsApiSourcesResponse> responseCall = NewsAPI.getNewsAPI().getSources();
        responseCall.enqueue(new Callback<NewsApiSourcesResponse>() {
            @Override
            public void onResponse(Call<NewsApiSourcesResponse> call, Response<NewsApiSourcesResponse> response) {
                List<Source> allSource = response.body().getSources();
                CommonUsage.setAllNewSource(allSource);
                ViewPagerAdapter viewPageAdapter = new ViewPagerAdapter(getSupportFragmentManager(),allSource);
                viewPager.setAdapter(viewPageAdapter);
                //Toast.makeText(SwipeHomeActivity.this, "Success Call", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<NewsApiSourcesResponse> call, Throwable t) {

            }
        });
    }

    public class ViewPagerAdapter extends FragmentStatePagerAdapter
    {
        List<Source> sources;

        public ViewPagerAdapter(FragmentManager fm, List<Source> sources) {
            super(fm);
            this.sources=sources;
        }
        @Override
        public Fragment getItem(int position) {
            return ListOfArticlesFragment.generateFragment(position);
        }

        @Override
        public int getCount() {
            return sources.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return sources.get(position).getName();
        }
    }


}
