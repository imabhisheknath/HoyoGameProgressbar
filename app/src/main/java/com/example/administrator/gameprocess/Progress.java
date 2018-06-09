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

    public Progress(@NonNull Context context) {
        super(context);

        ProgressDialogTheme progressDialogTheme = new ProgressDialogTheme();


        this.context = context;
        mDialog = new Dialog(context, R.style.cart_dialog);

        mDialog.setContentView(R.layout.activity_test);
        mDialog.setCanceledOnTouchOutside(true);
        if (mDialog.getWindow() != null) {
            View view = mDialog.getWindow().getDecorView();
            imgTest = (GifView) view.findViewById(R.id.imgTest);
            tvTime = (TextView) view.findViewById(R.id.tvTime);
            progressDialogTheme.receivetime(new receiveSecounds() {
                @Override
                public void receiveData(long sec) {

                    tvTime.setText(sec + "");
                }
            });
            imgTest.play();
          /*  try {
                GifDrawable gifFromResource = new GifDrawable(context.getResources(), R.raw.runcartoon);
                imgTest.setImageDrawable(gifFromResource);

            } catch (IOException e) {
                e.printStackTrace();

            }*/
        }


    }


    public void Show() {
        mDialog.show();
    }

    public void Dissmiss() {
        mDialog.dismiss();
    }
}
