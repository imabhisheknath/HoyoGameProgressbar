package com.example.administrator.gameprocess;

/**
 * Created by PC on 14-11-2017.
 */

import android.app.Dialog;
import android.content.Context;
import android.speech.tts.TextToSpeech;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.KeyboardShortcutGroup;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

import java.util.List;


public class Progress extends Dialog implements TextToSpeech.OnInitListener {
    Dialog mDialog;
    GifView imgTest;
    Context context;
    public TextView tvTime;


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


    @Override
    public void onInit(int i) {

    }

    @Override
    public void onProvideKeyboardShortcuts(List<KeyboardShortcutGroup> data, @Nullable Menu menu, int deviceId) {

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
