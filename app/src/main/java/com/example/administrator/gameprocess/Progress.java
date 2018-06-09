package com.example.administrator.gameprocess;

/**
 * Created by PC on 14-11-2017.
 */

import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.widget.TextView;


public class Progress extends Dialog {
    Dialog mDialog;
    GifView imgTest;
    Context context;
    public static TextView tvTime;


    public Progress(@NonNull Context context) {
        super(context);

        context.registerReceiver(mNotificationReceiver, new IntentFilter("ActionB"));

        this.context = context;
        mDialog = new Dialog(context, R.style.cart_dialog);

        mDialog.setContentView(R.layout.activity_test);
        mDialog.setCanceledOnTouchOutside(false);
        if (mDialog.getWindow() != null) {
            View view = mDialog.getWindow().getDecorView();
            imgTest = (GifView) view.findViewById(R.id.imgTest);
            tvTime = (TextView) view.findViewById(R.id.tvTime);

            imgTest.play();
        }


    }


    public void Show() {
        mDialog.show();
    }

    public void Dissmiss() {
        mDialog.dismiss();
    }


    private BroadcastReceiver mNotificationReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

            String enable = intent.getStringExtra("status");
            if (enable.equalsIgnoreCase("yes")) {
                tvTime.setVisibility(View.VISIBLE);
                tvTime.setText(intent.getStringExtra("tym"));
            }


        }
    };
}
