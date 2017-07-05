package com.example.davidtran.simpletwitter.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

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

public class SearchTweetFragment extends Fragment {
    private static final String SEARCH_KEYWORD = "search_keyword";
    ArrayList<Tweet> tweetArrayList = new ArrayList<>();
    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout mSwipeRefreshLayout;
    @BindView(R.id.rcTweetList) RecyclerView rcTweetList;
    @BindView(R.id.loadingBar)
    ProgressBar loadingBar;
    TweetAdapter tweetAdapter;
    LinearLayoutManager layoutManager;
    RestClient restClient = RestApplication.getRestClient();
    JsonParser jsonParser = new JsonParser();
    JsonObject jsonObject = new JsonObject();
    Tweet tweet = new Tweet();
    boolean isNewTweetPosted = false;
    boolean isSearched = false;

    public SearchTweetFragment() {
    }

    public static SearchTweetFragment newInstance(String searchKeyWord){
        SearchTweetFragment fragment = new SearchTweetFragment();
        Bundle bundle = new Bundle();
        bundle.putString(SEARCH_KEYWORD,searchKeyWord);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_hometimeline, container, false);
        ButterKnife.bind(this,view);

        SearchTweets();
        //getHTimeline(0);
        setUpAdapter();
        setUpListener();
        return view;

    }
    private void setUpListener(){

        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                tweetArrayList.clear();
                //getHTimeline(0);
                mSwipeRefreshLayout.setRefreshing(false);
            }
        });
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

       /* rcTweetList.addOnScrollListener(new EndlessRecyclerViewScrollListener(layoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount) {

                loadingBar.setVisibility(View.VISIBLE);

                getHTimeline(page);
                loadingBar.setVisibility(View.GONE);

            }
        });
*/


    }


    private void SearchTweets(){
        Bundle bundle = getArguments();
        String searchKeyWord = "";
        if(bundle!=null){
            searchKeyWord = bundle.getString(SEARCH_KEYWORD);
            if(searchKeyWord=="") return;
            restClient.getSearchTweet(searchKeyWord,20,new JsonHttpResponseHandler(){
                @Override
                public void onSuccess(int statusCode, Header[] headers, JSONObject response) {

                    JSONArray jsonArray = new JSONArray();
                    try {
                        jsonArray = response.getJSONArray("statuses");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    tweetArrayList.clear();
                    updateTweetList(jsonArray);
                    Log.d("An",jsonArray.toString());

                }

                @Override
                public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                    super.onFailure(statusCode, headers, throwable, errorResponse);
                }
            });
        }
    }
    private void updateTweetList(JSONArray jsonArray){
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