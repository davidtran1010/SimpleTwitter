package com.example.davidtran.simpletwitter.Activities;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.davidtran.simpletwitter.Fragments.HomeTimelineFragment;
import com.example.davidtran.simpletwitter.Fragments.TweetComposerFragment;
import com.example.davidtran.simpletwitter.R;
import com.example.davidtran.simpletwitter.Utils.Communicator;


public class MainActivity extends AppCompatActivity implements Communicator {
    Fragment timelineFragment = new HomeTimelineFragment();
    Fragment composerFragment = new TweetComposerFragment();
    boolean isComposerShowed = false;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        showHomeTimeLine();
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

    @Override
    public void notifyTweetPosted(boolean tweetPostStatus) {
        if (tweetPostStatus == true){
            HomeTimelineFragment homeTimelineFragment =
                    (HomeTimelineFragment) getSupportFragmentManager().findFragmentById(R.id.frag_containerList);

            homeTimelineFragment.notifyPostNewTweet();
        }
    }
}

