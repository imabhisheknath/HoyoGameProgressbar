package com.example.administrator.gameprocess;

import android.app.Activity;
import android.os.Handler;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends Activity {
    ProgressDialogTheme progressDialog;
    TextView tvTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvTitle = (TextView) findViewById(R.id.tvTitle);

        progressDialog = new ProgressDialogTheme(MainActivity.this);
        progressDialog.setResponseListener(listner);
        progressDialog.setTimeOutTime(4000);
        // progressDialog.setCountDownTime(5L);
          progressDialog.enableCountText();
         progressDialog.callCountDownAutoMatically();
        progressDialog.startProgressDialog();
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

              //  progressDialog.preparaationDone();

             //   progressDialog.startCountDown();
                  progressDialog.preparationFailed();


            }
        }, 5000);

    }


    ProgressDialogTheme.onProgressDialogTimeoutListner listner = new ProgressDialogTheme.onProgressDialogTimeoutListner() {
        @Override
        public void onTimeOut() {


            tvTitle.setText("timout");
        }

        @Override
        public void onCancel() {

            tvTitle.setText("cancel");
        }

        @Override
        public void onSucess() {

            Log.e("all set", "all sert");

            tvTitle.setVisibility(View.VISIBLE);
            tvTitle.setText("ready");
        }

        @Override
        public void secoundsForTimeout(long sec) {
            tvTitle.setText("s" + sec);
            Log.e("sec", "" + sec);
        }

        @Override
        public void onForcingCountdownstart() {
            tvTitle.setText("invalid Location");

            Log.e("all set", "all Location");
        }

    };

}
