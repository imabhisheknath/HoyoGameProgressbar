package com.example.administrator.gameprocess;

/**
 * Created by PC on 14-11-2017.
 */

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.TextView;


public class Progress extends Dialog {
    Dialog mDialog;
    GifView imgTest;
    Context context;
    public static TextView tvTime;

    public receiveSecounds receiveSecounds;


    public Progress(@NonNull Context context) {
        super(context);


        this.context = context;
        mDialog = new Dialog(context, R.style.cart_dialog);


        receiveSecounds = new receiveSecounds() {
            @Override
            public void receiveData(long sec) {

                tvTime.setText(sec + "");
            }
        };


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


    public receiveSecounds receiveSecounds() {
        return receiveSecounds;
    }
}
