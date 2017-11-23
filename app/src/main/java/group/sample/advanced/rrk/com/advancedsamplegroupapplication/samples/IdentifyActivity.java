package group.sample.advanced.rrk.com.advancedsamplegroupapplication.samples;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresPermission;
import android.support.v4.app.ActivityCompat;
import android.telephony.TelephonyManager;
import android.widget.TextView;

import java.util.UUID;

import butterknife.BindView;
import butterknife.ButterKnife;
import group.sample.advanced.rrk.com.advancedsamplegroupapplication.BaseActivity;
import group.sample.advanced.rrk.com.advancedsamplegroupapplication.BuildConfig;
import group.sample.advanced.rrk.com.advancedsamplegroupapplication.R;

/**
 * Created by RyoRyeong Kim on 2017-11-23.
 */

public class IdentifyActivity extends BaseActivity {

    @BindView(R.id.tvTelePhonyValue)
    TextView tvTelePhonyValue;

    @BindView(R.id.tvAndroidIDValue)
    TextView tvAndroidIDValue;

     @BindView(R.id.tvSerialNumber)
    TextView tvSerialNumber;

    @BindView(R.id.tvUUIDValue)
    TextView tvUUIDValue;

    @BindView(R.id.tvPackageName)
    TextView tvPackageName;

    @BindView(R.id.tvApplicationID)
    TextView tvApplicationID;


    int requestCode = 1001;


    String [] permisiosns = {
            Manifest.permission.READ_PHONE_STATE
    };
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView( R.layout.activity_identify );

        ButterKnife.bind( this );

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            boolean result = ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) == PackageManager.PERMISSION_GRANTED;

            if (result) {
                initUI();
            } else {
                requestPermissions(permisiosns, requestCode);

            }
        }else {
            initUI();
        }

    }


    @SuppressLint("MissingPermission")
    public void initUI() {

            TelephonyManager telephonyManager = ( TelephonyManager ) getSystemService( this.TELEPHONY_SERVICE );


            final String tmDevice, tmSerial, androidID;
            tmDevice = "" + telephonyManager.getDeviceId();
            tmSerial = "" + telephonyManager.getSimSerialNumber();
            androidID = android.provider.Settings.Secure.getString( getContentResolver(), Settings.Secure.ANDROID_ID );

            UUID deviceUuid = new UUID(androidID.hashCode(), ((long)tmDevice.hashCode() << 32) | tmSerial.hashCode());
            String deviceId = deviceUuid.toString();

            tvTelePhonyValue.setText( tmDevice );
            tvAndroidIDValue.setText( androidID );
            tvSerialNumber.setText( tmSerial );
            tvUUIDValue.setText( deviceId );

            tvPackageName.setText( getPackageName() );
            tvApplicationID.setText(BuildConfig.APPLICATION_ID );

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if( requestCode == this.requestCode ){

            for( int grant : grantResults ){
                if( grant  != PackageManager.PERMISSION_GRANTED ){
                    return;
                }
            }

            initUI();
        }
    }
}
