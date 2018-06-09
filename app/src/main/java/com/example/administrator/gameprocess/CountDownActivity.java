package com.example.administrator.gameprocess;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.TextView;

public class CountDownActivity extends Activity {

    TextView tvTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_count_down);
        tvTitle = findViewById(R.id.tvTitle);

        Intent intent = getIntent();

        String tym = intent.getStringExtra("time");

        long starttym = Long.valueOf(tym);


        CountDownAnimation countDownAnimation = new CountDownAnimation(tvTitle, starttym);
        countDownAnimation.start();
        // Use scale animation
        Animation scaleAnimation = new ScaleAnimation(1.0f, 0.0f, 1.0f, 0.0f,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        countDownAnimation.setAnimation(scaleAnimation);

        countDownAnimation.setCountDownListener(new CountDownAnimation.CountDownListener() {
            @Override
            public void onCountDownEnd(CountDownAnimation animation) {


                Intent intent = new Intent();
                intent.setAction("ActionA");
                sendBroadcast(intent);
                finish();

            }
        });

    }








}
