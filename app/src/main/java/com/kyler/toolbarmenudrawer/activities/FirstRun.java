package com.kyler.toolbarmenudrawer.activities;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

import com.kyler.toolbarmenudrawer.R;
import com.kyler.toolbarmenudrawer.ToolbarMenudrawer;

/**
 * Created by kyler on 10/24/14.
 */

public class FirstRun extends Activity {
    private Animation animFadeIn;

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.white_teal_layout);

        View decorView = getWindow().getDecorView();

        if (Build.VERSION.SDK_INT == Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(getResources().getColor(R.color.teal));

            decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);

        } else if (Build.VERSION.SDK_INT == Build.VERSION_CODES.KITKAT) {
            decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_IMMERSIVE);

        }

//      getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);

        final Button continueButton = (Button) findViewById(R.id.splashContinueButton);
        animFadeIn = AnimationUtils.loadAnimation(this, android.R.anim.fade_in);
        animFadeIn.setDuration(2700);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                continueButton.startAnimation(animFadeIn);
                continueButton.setVisibility(View.VISIBLE);

            }
        }, 3550);

        continueButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent continueIntent = new Intent(FirstRun.this, ToolbarMenudrawer.class);
                startActivity(continueIntent);
                finish();

            }
        });

    }
}
