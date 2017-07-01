package com.example.davidtran.simpletwitter.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.davidtran.simpletwitter.Models.RestApplication;
import com.example.davidtran.simpletwitter.Models.RestClient;
import com.example.davidtran.simpletwitter.R;
import com.example.davidtran.simpletwitter.Utils.Communicator;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import cz.msebera.android.httpclient.Header;

/**
 * Created by davidtran on 7/1/17.
 */

public class TweetComposerFragment extends Fragment {
    @BindView(R.id.btnTweet)
    Button btnTweet;
    @BindView(R.id.txtMyTweetContent)
    EditText txtMyTweetContent;
    @BindView(R.id.tvWordNumber)
    TextView tvWordNumber;
    private Communicator comm = null;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_newtweet,container,false);
        ButterKnife.bind(this,view);

        setUpListener();

        txtMyTweetContent.requestFocus();
        showKeyBoard();

        return view;
    }
    private void setUpListener(){
        btnTweet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tweetContent = txtMyTweetContent.getText().toString();
                postTweet(tweetContent);
            }
        });
        txtMyTweetContent.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //String[] strings = s.toString().split("\\s");
                tvWordNumber.setText(""+s.length());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }

    private void postTweet(String postedTweet){
        RestClient restClient = RestApplication.getRestClient();

        restClient.postTweet(postedTweet, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                Log.d("An", response.toString());
                comm = (Communicator) getActivity();
                comm.notifyTweetPosted(true);
                closeKeyBoard();
                getActivity().onBackPressed();

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);
            }
        });
    }
    private void closeKeyBoard(){
        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);
    }
    private void showKeyBoard(){
        InputMethodManager imm = (InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED,InputMethodManager.HIDE_IMPLICIT_ONLY);
    }

}
