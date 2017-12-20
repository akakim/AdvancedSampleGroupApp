package group.sample.advanced.rrk.com.advancedsamplegroupapplication;


import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ProgressBar;

import group.sample.advanced.rrk.com.advancedsamplegroupapplication.views.BigProgressDialog;


/**
 * @author KIM
 * @version 0.0.1
 * @date 2017-11-23
 * @since 0.0.1
 */

public class BaseActivity extends AppCompatActivity implements DialogInterface.OnCancelListener{


    protected     Handler baseHandler;

    protected BigProgressDialog progressBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        progressBar = new BigProgressDialog(this,true,this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();



    }

    @Override
    public void startActivity(Intent intent) {
        super.startActivity(intent);
        overridePendingTransition(0,R.anim.move_right_out_activity);
    }

    @Override
    public void startActivityForResult(Intent intent, int requestCode) {
        super.startActivityForResult(intent, requestCode);
        overridePendingTransition(0,R.anim.move_right_out_activity);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.move_left_in_activity, R.anim.move_right_out_activity);
    }

    @Override
    public void onCancel(DialogInterface dialog) {
        dialog.dismiss();
    }
}
