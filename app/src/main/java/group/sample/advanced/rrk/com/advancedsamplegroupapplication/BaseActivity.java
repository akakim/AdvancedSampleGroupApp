package group.sample.advanced.rrk.com.advancedsamplegroupapplication;


import android.Manifest;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

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


    String permissions [] = {
//            Manifest.permission.READ_EXTERNAL_STORAGE,
//            Manifest.permission.WRITE_EXTERNAL_STORAGE,
//            Manifest.permission.READ_PHONE_STATE,
            Manifest.permission.SYSTEM_ALERT_WINDOW
    };

    final int ALL_PERMISSION_REQUEST_CODE = 2000;
    final int OVERLAY_REQUEST_CODE  = 3000;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        progressBar = new BigProgressDialog(this,true,this);
//        if( Build.VERSION.SDK_INT >= Build.VERSION_CODES.M ) {
//            boolean isNotAllPermit = true;
//            for (String permit : permissions) {
//
//                isNotAllPermit &= ( PackageManager.PERMISSION_GRANTED != ContextCompat.checkSelfPermission(this, permit) );
//
//            }
//
//            if( isNotAllPermit ){
//                requestPermissions( permissions, ALL_PERMISSION_REQUEST_CODE );
//            }else {
//                Settings.canDrawOverlays(this );
//
//            }

//            Settings.canDrawOverlays( this )

        startOverlayWindowService( this );
        }

    public void startOverlayWindowService( Context context){
            if( Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && !Settings.canDrawOverlays(context) ){
                onObtainingPermissionOverlayWindow();
            }
    }


    @TargetApi(Build.VERSION_CODES.M)
    public void onObtainingPermissionOverlayWindow(){
        Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION, Uri.parse("package:"+getPackageName()));
        startActivityForResult(intent , OVERLAY_REQUEST_CODE);
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


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        Log.d(getClass().getSimpleName(),"onRequestPermission");
        if( requestCode == this.ALL_PERMISSION_REQUEST_CODE){

            boolean isPermitted = true;
            for (int k = 0; k< permissions.length; k++){


                    isPermitted &= (PackageManager.PERMISSION_GRANTED == grantResults[k]);

                    boolean result = (PackageManager.PERMISSION_GRANTED == grantResults[k]);
                    Log.d(getClass().getSimpleName(),permissions[k] + " result :" + result );
                Log.d(getClass().getSimpleName(),isPermitted+"  is all permiteed result ? ");
            }


            if ( !isPermitted ){
                Toast.makeText(this,"앱의 알람권한을 이용하기 위해서 권한이 필요합니다. ",Toast.LENGTH_SHORT ).show();
            }

        }
    }
}
