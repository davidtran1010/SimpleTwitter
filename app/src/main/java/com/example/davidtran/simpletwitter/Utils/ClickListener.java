package com.example.davidtran.simpletwitter.Utils;

import android.view.View;

/**
 * Created by davidtran on 7/6/17.
 */

public interface ClickListener{
    public void onClick(View view,int position);
    public void onLongClick(View view, int position);
}