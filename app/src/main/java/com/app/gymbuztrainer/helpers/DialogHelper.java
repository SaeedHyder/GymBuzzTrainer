package com.app.gymbuztrainer.helpers;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import com.app.gymbuztrainer.R;

public class DialogHelper {
    private Dialog dialog;
    private Context context;

    public DialogHelper(Context context) {
        this.context = context;
        this.dialog = new Dialog(context);
    }


    public Dialog initlogout(int layoutID, View.OnClickListener onokclicklistener, View.OnClickListener oncancelclicklistener) {
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        this.dialog.setContentView(layoutID);
        Button okbutton = (Button) dialog.findViewById(R.id.btn_yes);
        okbutton.setOnClickListener(onokclicklistener);
        Button cancelbutton = (Button) dialog.findViewById(R.id.btn_No);
        cancelbutton.setOnClickListener(oncancelclicklistener);
        return this.dialog;
    }

    public Dialog initRequestDialoge(int layoutID, View.OnClickListener acceptclicklistener, View.OnClickListener rejectClickListener) {
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        this.dialog.setContentView(layoutID);
        Button accept = (Button) dialog.findViewById(R.id.btn_accept);
        accept.setOnClickListener(acceptclicklistener);
        Button reject = (Button) dialog.findViewById(R.id.btn_reject);
        reject.setOnClickListener(rejectClickListener);
        return this.dialog;
    }

    public Dialog initJobDoneDialoge(int layoutID, View.OnClickListener acceptclicklistener, View.OnClickListener rejectClickListener) {
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        this.dialog.setContentView(layoutID);
        Button accept = (Button) dialog.findViewById(R.id.btn_yes);
        accept.setOnClickListener(acceptclicklistener);
        Button reject = (Button) dialog.findViewById(R.id.btn_no);
        reject.setOnClickListener(rejectClickListener);
        return this.dialog;
    }


    public void showDialog() {
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
    }

    public void setCancelable(boolean isCancelable) {
        dialog.setCancelable(isCancelable);
        dialog.setCanceledOnTouchOutside(isCancelable);
    }

    public void hideDialog() {
        dialog.dismiss();
    }

}
