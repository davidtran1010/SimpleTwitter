package com.example.davidtran.simpletwitter.Activities;

import android.app.ActionBar;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.davidtran.simpletwitter.Fragments.HomeTimelineFragment;
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
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;
import jp.wasabeef.picasso.transformations.RoundedCornersTransformation;


public class MainActivity extends AppCompatActivity implements Communicator {

    RestClient restClient = RestApplication.getRestClient();
    Fragment timelineFragment = new HomeTimelineFragment();
    JsonParser jsonParser = new JsonParser();
    JsonObject jsonObject = new JsonObject();
    boolean isComposerShowed = false;
    OwnerUser ownerUser;
    private static final String SEARCH_KEYWORD = "search_keyword";
    private static final String OWNER_USER_KEY = "owner_user_key";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        showOwnerUser();
        showHomeTimeLine();
    }
    private void setUpActionBar(OwnerUser ownerUser){
        ViewGroup view = (ViewGroup) getLayoutInflater().inflate(R.layout.custom_actionbar, null);
        final android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(false);
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setCustomView(view);

        ImageView ivIcon = (ImageView) view.findViewById(R.id.OwnerProfilePicture);
        Picasso.with(getApplicationContext()).load(ownerUser.getProfileImageUrl())
                .transform(new RoundedCornersTransformation(5,5))
                .into(ivIcon);

        ivIcon.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"Show profile info",Toast.LENGTH_SHORT).show();
            }
        });

    }
    private void showOwnerUser(){
        getOwnerUser();
       // setUpActionBar();
    }

    private void getOwnerUser(){
        restClient.getOwnerUser(new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                JsonObject gJsonObject;
                gJsonObject = (JsonObject) jsonParser.parse(response.toString());
                ownerUser = mJsonParser.fromJsonObjectToOwnerUser(gJsonObject);

                setUpActionBar(ownerUser);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);
            }


        });
    }
    private void hideTweetComposer(){
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frag_containerCompose, new Fragment()).commit();
        isComposerShowed = false;
    }

    private void showTweetComposer() {

        if (isComposerShowed) {
            Log.d("An","Composer hidden");
            hideTweetComposer();
            return;
        }
        Log.d("An","Composer show");
        isComposerShowed = true;
        Fragment composerFragment = new TweetComposerFragment().newInstance(ownerUser);

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.addToBackStack(null).replace(R.id.frag_containerCompose, composerFragment).commit();
    }

    private void showHomeTimeLine() {

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frag_containerList, timelineFragment).commit();
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
                showTweetComposer();
                return true;

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {

        return super.onPrepareOptionsMenu(menu);
    }

    private void searchViewListener(MenuItem itemSearch){
        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(itemSearch);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                searchView.clearFocus();
                Search(query);
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
        if (tweetPostStatus == true){
            HomeTimelineFragment homeTimelineFragment =
                    (HomeTimelineFragment) getSupportFragmentManager().findFragmentById(R.id.frag_containerList);

            homeTimelineFragment.notifyPostNewTweet();
        }
    }


    public void Search(String query) {
        {
            HomeTimelineFragment timelineFragment = HomeTimelineFragment.newInstance(query);

            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.frag_containerList, timelineFragment).commit();
            timelineFragment.notifySearch();
        }
    }
}

