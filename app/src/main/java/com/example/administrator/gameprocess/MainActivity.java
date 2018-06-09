package com.example.administrator.gameprocess;

import android.app.Activity;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends Activity {
    ProgressDialogTheme progressDialog;
    DisplayCountDown displayCountDown;
    TextView tvTitle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvTitle = (TextView) findViewById(R.id.tvTitle);

        progressDialog = new ProgressDialogTheme(MainActivity.this);
        progressDialog.setResponseListener(listner);
        progressDialog.setTimeOutTime(10000);
        progressDialog.setCountDownTime(5L);
        //  progressDialog.callCountDownAutoMatically();
        progressDialog.start();
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                progressDialog.LocationSucess();
                progressDialog.startCountDown();

            }
        }, 5000);

    }




    ProgressDialogTheme.onProgressDialogTimeoutListner listner = new ProgressDialogTheme.onProgressDialogTimeoutListner() {
        @Override
        public void onTimeOut() {


            tvTitle.setText("timout");
        }

        @Override
        public void onCancelbyLocationFailed() {

            tvTitle.setText("cancel");
        }

        @Override
        public void onAllSet() {

            tvTitle.setVisibility(View.VISIBLE);
            tvTitle.setText("ready");
        }

        @Override
        public void secoundRemainngforCountDown(long sec) {
            tvTitle.setText("s" + sec);
            Log.e("sec", "" + sec);
        }

        @Override
        public void onInvalidCountdownStartdueToLocation() {
            tvTitle.setText("invalid Location");
        }

    };

}
