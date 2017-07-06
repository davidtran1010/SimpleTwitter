package com.example.davidtran.simpletwitter.Utils;

import android.content.res.Resources;

/**
 * Created by davidtran on 7/6/17.
 */

public class ScreenCalculator {
    public static int getScreenWidth() {
        return Resources.getSystem().getDisplayMetrics().widthPixels;
    }

    public static int getScreenHeight() {
        return Resources.getSystem().getDisplayMetrics().heightPixels;
    }
}
