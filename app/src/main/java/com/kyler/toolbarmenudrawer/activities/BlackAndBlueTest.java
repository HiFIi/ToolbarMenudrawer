package com.kyler.toolbarmenudrawer.activities;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;

import com.kyler.toolbarmenudrawer.R;

/**
 * Created by kyler on 10/25/14.
 */
public class BlackAndBlueTest extends Activity {

    /** This is just a test Activity I created that
     * basically displays a black theme with a blue StatusBar
     * @param savedInstanceState
     */

    @SuppressLint("InlinedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.black_and_blue_test);
    }
}