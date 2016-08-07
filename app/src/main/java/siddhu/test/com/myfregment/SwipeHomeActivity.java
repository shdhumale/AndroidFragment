package siddhu.test.com.myfregment;

import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.content.SharedPreferencesCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.io.IOException;
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
        MyAsyncTask myAsyncTask =  new MyAsyncTask();
        myAsyncTask.execute();


  /*      Call<NewsApiSourcesResponse> responseCall = NewsAPI.getNewsAPI().getSources();
        responseCall.enqueue(new Callback<NewsApiSourcesResponse>() {
            @Override
            public void onResponse(Call<NewsApiSourcesResponse> call, Response<NewsApiSourcesResponse> response) {
                List<Source> allSource = response.body().getSources();
                CommonUsage.setAllNewSource(allSource);
                viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                    @Override
                    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                        //Log.i(TAG, "PageScroller" + position);
                        System.out.println("PageScroller : " + position);
                    }

                    @Override
                    public void onPageSelected(int position) {
                        //Log.i(TAG, "PageSelected" + position);
                        saveInPreference(position);
                        System.out.println("PageSelected : " + position);
                    }

                    @Override
                    public void onPageScrollStateChanged(int state) {
                        //Log.i(TAG, "PageScrollStateChanged" + position);
                        System.out.println("PageScrollStateChanged : "+state);
                    }
                });
                ViewPagerAdapter viewPageAdapter = new ViewPagerAdapter(getSupportFragmentManager(), allSource);
                viewPager.setAdapter(viewPageAdapter);
                SharedPreferences sharedPreference = PreferenceManager.getDefaultSharedPreferences(SwipeHomeActivity.this);
                int position = sharedPreference.getInt(KEY_POSITION, DEFAULT_POSITION);
                if (position != 0)
                {
                    viewPager.setCurrentItem(position);
                }

                //Toast.makeText(SwipeHomeActivity.this, "Success Call", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<NewsApiSourcesResponse> call, Throwable t) {

            }
        });*/
    }

    class MyAsyncTask extends AsyncTask<Void, Void, NewsApiSourcesResponse>{

        @Override
        protected NewsApiSourcesResponse doInBackground(Void... voids) {
            Call<NewsApiSourcesResponse> responseCall = NewsAPI.getNewsAPI().getSources();
            Response<NewsApiSourcesResponse> response = null;
            try {
                response = responseCall.execute();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return response.body();
        }

        @Override
        protected void onPostExecute(NewsApiSourcesResponse newsApiSourcesResponse) {
            super.onPostExecute(newsApiSourcesResponse);
            List<Source> allSource = newsApiSourcesResponse.getSources();
            CommonUsage.setAllNewSource(allSource);
            viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                    //Log.i(TAG, "PageScroller" + position);
                    System.out.println("PageScroller : " + position);
                }

                @Override
                public void onPageSelected(int position) {
                    //Log.i(TAG, "PageSelected" + position);
                    saveInPreference(position);
                    System.out.println("PageSelected : " + position);
                }

                @Override
                public void onPageScrollStateChanged(int state) {
                    //Log.i(TAG, "PageScrollStateChanged" + position);
                    System.out.println("PageScrollStateChanged : "+state);
                }
            });
            ViewPagerAdapter viewPageAdapter = new ViewPagerAdapter(getSupportFragmentManager(), allSource);
            viewPager.setAdapter(viewPageAdapter);
            SharedPreferences sharedPreference = PreferenceManager.getDefaultSharedPreferences(SwipeHomeActivity.this);
            int position = sharedPreference.getInt(KEY_POSITION, DEFAULT_POSITION);
            if (position != 0)
            {
                viewPager.setCurrentItem(position);
            }
        }
    }


    public static final String KEY_POSITION = "storedposition";
    public static final int DEFAULT_POSITION = 0;

    public void saveInPreference(int position)
    {
        SharedPreferences sharePreferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = sharePreferences.edit();
        editor.putInt(KEY_POSITION, position);
        editor.commit();
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
