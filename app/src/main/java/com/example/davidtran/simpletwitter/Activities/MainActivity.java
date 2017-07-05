package com.example.davidtran.simpletwitter.Activities;

import android.app.ActionBar;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.support.design.widget.TabLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.davidtran.simpletwitter.Adapters.CustomPagerAdapter;
import com.example.davidtran.simpletwitter.Fragments.HomeTimelineFragment;
import com.example.davidtran.simpletwitter.Fragments.SearchTweetFragment;
import com.example.davidtran.simpletwitter.Fragments.TweetComposerFragment;
import com.example.davidtran.simpletwitter.Models.OwnerUser;
import com.example.davidtran.simpletwitter.Models.RestApplication;
import com.example.davidtran.simpletwitter.Models.RestClient;
import com.example.davidtran.simpletwitter.R;
import com.example.davidtran.simpletwitter.Utils.Communicator;
import com.example.davidtran.simpletwitter.Utils.mJsonParser;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.melnykov.fab.FloatingActionButton;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cz.msebera.android.httpclient.Header;
import jp.wasabeef.picasso.transformations.RoundedCornersTransformation;


public class MainActivity extends AppCompatActivity implements Communicator {
    @BindView(R.id.fab_compose)
    android.support.design.widget.FloatingActionButton fab_compose;
    @BindView(R.id.tabLayout)
    TabLayout tabLayout;

    @BindView(R.id.viewPager)
    ViewPager viewPager;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.ownerUserLogo)
    ImageView ownerUserLogo;

    @BindView(R.id.tabTitle)
    TextView tabTitle;

    @BindView(R.id.searchView)
    SearchView searchView;

    @BindView(R.id.nestedScrollView)
    NestedScrollView nestedScrollView;

    RestClient restClient = RestApplication.getRestClient();

    Fragment timelineFragment = new HomeTimelineFragment();
    Fragment searchTweetFragment = SearchTweetFragment.newInstance("");
    ArrayList<Fragment> fragmentArrayList = new ArrayList<>();
    CustomPagerAdapter customPagerAdapter;

    JsonParser jsonParser = new JsonParser();
    JsonObject jsonObject = new JsonObject();

    OwnerUser ownerUser;
    boolean isComposerShowed = false;
    private static final String SEARCH_KEYWORD = "search_keyword";
    private static final String OWNER_USER_KEY = "owner_user_key";

    int[] icon = new int[]
            {R.drawable.ic_home
                    , R.drawable.ic_search
                    , R.drawable.ic_notifications
                    , R.drawable.ic_messages};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        showOwnerUser();


        setupTabLayout();
        setupListener();
        setupSearchView();


    }
    private void setupTabLayout(){
        fragmentArrayList.add(timelineFragment);
        fragmentArrayList.add(searchTweetFragment);

        customPagerAdapter = new CustomPagerAdapter(getSupportFragmentManager(),fragmentArrayList);
        viewPager.setAdapter(customPagerAdapter);

        viewPager.setOffscreenPageLimit(4);
        tabLayout.setupWithViewPager(viewPager);

        for (int i = 0; i < 4; i++) {
            tabLayout.getTabAt(i).setIcon(icon[i]);
        }
    }
    private void setupToolbar(OwnerUser ownerUser) {

        Picasso.with(getApplicationContext()).load(this.ownerUser.getProfileImageUrl())
                //.transform(new RoundedCornersTransformation(5,5))
                .into(ownerUserLogo);
    }

    private void setupListener() {
        setupFloatButtonListener();
        setupTabLayoutListener();
    }
    private void setupFloatButtonListener(){
        fab_compose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Show tweet compose", Toast.LENGTH_SHORT).show();
                showTweetComposeDialog();


            }
        });
    }
    public void showTweetComposeDialog() {
        DisplayMetrics metrics = getResources().getDisplayMetrics();
        int width = metrics.widthPixels;
        int height = metrics.heightPixels;

        FragmentManager manager = getSupportFragmentManager();

        TweetComposerFragment mydialog = TweetComposerFragment.newInstance(ownerUser);

        mydialog.show(manager, "mydialog");


    }
    private void setupTabLayoutListener() {
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition();
                switch (position) {
                    case 0:
                        tabTitle.setText("Home");
                        searchView.setVisibility(View.GONE);
                        ownerUserLogo.setVisibility(View.VISIBLE);
                        break;
                    case 1:

                        tabTitle.setText("");
                        searchView.setVisibility(View.VISIBLE);
                        searchView.setIconified(false);
                        ownerUserLogo.setVisibility(View.GONE);

                        break;
                    case 2:
                        tabTitle.setText("Notifications");
                        searchView.setVisibility(View.GONE);
                        ownerUserLogo.setVisibility(View.VISIBLE);

                        break;
                    case 3:
                        tabTitle.setText("Messages");
                        searchView.setVisibility(View.GONE);
                        ownerUserLogo.setVisibility(View.VISIBLE);
                        break;
                    default:  tabTitle.setText("Twitter");

                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    private void showOwnerUser() {
        getOwnerUser();
    }

    private void getOwnerUser() {
        restClient.getOwnerUser(new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                JsonObject gJsonObject;
                gJsonObject = (JsonObject) jsonParser.parse(response.toString());
                ownerUser = mJsonParser.fromJsonObjectToOwnerUser(gJsonObject);

                setupToolbar(ownerUser);

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);
            }


        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.mainmenu, menu);
        MenuItem itemSearch = menu.findItem(R.id.action_search);
        searchViewListener(itemSearch);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch (id) {

            case R.id.sweet_compose:
                //showTweetComposer();
                return true;

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {

        return super.onPrepareOptionsMenu(menu);
    }
    private void setupSearchView(){
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                searchView.clearFocus();
                Search(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }
    private void searchViewListener(MenuItem itemSearch) {
        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(itemSearch);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                searchView.clearFocus();
                //Search(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                //loadingBar.setVisibility(View.VISIBLE);
                return true;
            }

        });
    }

    @Override
    public void notifyTweetPosted(boolean tweetPostStatus) {
        /*if (tweetPostStatus == true){
            HomeTimelineFragment homeTimelineFragment =
                    (HomeTimelineFragment) getSupportFragmentManager().findFragmentById(R.id.frag_containerList);

            homeTimelineFragment.notifyPostNewTweet();
        }*/
    }


    public void Search(String query) {
        {   SearchTweetFragment searchTweetFragment1 = SearchTweetFragment.newInstance(query);

            searchTweetFragment = searchTweetFragment1;

            fragmentArrayList.set(1,searchTweetFragment);
            customPagerAdapter.notifyDataSetChanged();

            // reRender icon for tablayout after notify adapter change
            for (int i = 0; i < 4; i++) {
                tabLayout.getTabAt(i).setIcon(icon[i]);
            }

        }
    }
}

