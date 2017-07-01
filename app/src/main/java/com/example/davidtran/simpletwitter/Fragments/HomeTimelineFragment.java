package com.example.davidtran.simpletwitter.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.davidtran.simpletwitter.Adapters.EndlessRecyclerViewScrollListener;
import com.example.davidtran.simpletwitter.Adapters.TweetAdapter;
import com.example.davidtran.simpletwitter.Utils.mJsonParser;
import com.google.gson.JsonObject;
import com.example.davidtran.simpletwitter.Models.RestApplication;
import com.example.davidtran.simpletwitter.Models.RestClient;
import com.example.davidtran.simpletwitter.Models.Tweet;
import com.example.davidtran.simpletwitter.R;
import com.google.gson.JsonParser;
import com.loopj.android.http.JsonHttpResponseHandler;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import cz.msebera.android.httpclient.Header;


/**
 * Created by davidtran on 6/30/17.
 */

public class HomeTimelineFragment extends Fragment {
    ArrayList<Tweet> tweetArrayList = new ArrayList<>();
    @BindView(R.id.rcTweetList) RecyclerView rcTweetList;
    @BindView(R.id.loadingBar)
    ProgressBar loadingBar;
    TweetAdapter tweetAdapter;
    LinearLayoutManager layoutManager;
    RestClient restClient = RestApplication.getRestClient();
    boolean isNewTweetPosted = false;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_hometimeline, container, false);
        ButterKnife.bind(this,view);

        /**************************/
        getHTimeline(0);
        //tweetAdapter.notifyItemRangeInserted(tweetAdapter.getItemCount(),5);
        setUpAdapter();

        return view;

    }
    private void setUpAdapter(){
        tweetAdapter = new TweetAdapter(tweetArrayList,getContext());
        // Setup layout manager for items
        layoutManager = new LinearLayoutManager(getContext());
        // Control orientation of the items
        // also supports LinearLayoutManager.HORIZONTAL
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        // Set layout manager to position the items
        // Attach the layout manager to the recycler view
        rcTweetList.setLayoutManager(layoutManager);

        rcTweetList.setAdapter(tweetAdapter);


        rcTweetList.addOnScrollListener(new EndlessRecyclerViewScrollListener(layoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount) {

                loadingBar.setVisibility(View.VISIBLE);

                getHTimeline(page);
                loadingBar.setVisibility(View.GONE);

            }
        });
    }
    public void getHTimeline(int page) {


        restClient.getHomeTimeline(page, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray json) {

                Log.d("An", json.toString());


                if(isNewTweetPosted == true){
                    updatePostedTweet(json);
                    isNewTweetPosted = false;
                }
                else{
                    getTweetList(json);
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);
            }
        });
    }
    public void notifyPostNewTweet(){
       // rcTweetList.swapAdapter(tweetAdapter, true);
        //rcTweetList.scrollBy(0, 0);
        isNewTweetPosted = true;
        getHTimeline(0);
        tweetAdapter.notifyItemInserted(0);
        rcTweetList.scrollToPosition(0);
    }
    private void updatePostedTweet(JSONArray jsonArray){
        JsonParser jsonParser = new JsonParser();
        JsonObject jsonObject = new JsonObject();
        Tweet tweet = new Tweet();
        try {
            jsonObject = (JsonObject) jsonParser.parse(jsonArray.get(0).toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        tweet = mJsonParser.fromJsonObjectToTweet(jsonObject);

        tweetArrayList.add(0,tweet);
        tweetAdapter.notifyDataSetChanged();
    }
    private void getTweetList(JSONArray jsonArray) {
        JsonParser jsonParser = new JsonParser();
        JsonObject jsonObject = new JsonObject();
        Tweet tweet = new Tweet();

        for (int i = 0; i < jsonArray.length(); i++) {

            try {
                jsonObject = (JsonObject) jsonParser.parse(jsonArray.get(i).toString());
            } catch (JSONException e) {
                e.printStackTrace();
            }
            tweet = mJsonParser.fromJsonObjectToTweet(jsonObject);
            tweetArrayList.add(tweet);
            tweetAdapter.notifyDataSetChanged();
        }
    }


}
