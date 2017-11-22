package group.sample.advanced.rrk.com.advancedsamplegroupapplication.utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Environment;
import android.os.Looper;
import android.util.Log;
import android.view.Surface;
import android.view.WindowManager;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import group.sample.advanced.rrk.com.advancedsamplegroupapplication.BuildConfig;
import group.sample.advanced.rrk.com.advancedsamplegroupapplication.R;

/**
 * @author KIM
 * @version 0.0.1
 * @date 2017-11-12
 * @since 0.0.1
 */

public class Utils {


    public static final String preFix = "KRFIX_";


    @SuppressWarnings({"ConstantConditions", "ResultOfMethodCallIgnored"})
    public static File makeTempFile(Context context, String extension ){
        final String timeStamp =
                new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault())
                        .format(new Date());

        File parent =
                new File(Environment.getExternalStorageDirectory(),context.getString(R.string.app_name));

        parent.mkdirs();
        return new File(parent,preFix + timeStamp + extension);
    }


    public static InputStream openStream(Context context,Uri uri ) throws FileNotFoundException{
        if(uri == null){
            return null;
        }

        if( uri.getScheme() == null || uri.getScheme().equalsIgnoreCase("file")){
            return new FileInputStream( uri.getPath());
        } else {
            return context.getContentResolver().openInputStream( uri );
        }
    }
    public static void log(String context, String message , Object... formatArgs){

        if ( BuildConfig.DEBUG ) {
            if (formatArgs != null && formatArgs.length > 0) {
                Log.d(context,String.format(message,formatArgs));
            } else {
                Log.d(context,message);
            }
        }
    }
    public static void lockOrientation(Activity activity){
        int orientation;
        int rotation =
                ((WindowManager) activity.getSystemService( activity.WINDOW_SERVICE))
                .getDefaultDisplay()
                .getRotation();

        switch (rotation) {
            case Surface.ROTATION_0:
                orientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT;
                break;
            case Surface.ROTATION_90:
                orientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE;
                break;
            case Surface.ROTATION_180:
                orientation = ActivityInfo.SCREEN_ORIENTATION_REVERSE_PORTRAIT;
                break;
            default:
                orientation = ActivityInfo.SCREEN_ORIENTATION_REVERSE_LANDSCAPE;
                break;
        }

        activity.setRequestedOrientation( orientation );
    }


    public static void unlockOrientation( Activity activity) {
        activity.setRequestedOrientation( ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED );
    }


    public static void showMemoryError(Activity context){

        Exception exception = new Exception(
                "You've run out of Ram for processing image; " +
                        "i'm working to I'm working to improve memory usage! " +
                        "Sit tight while this app is in beta."

        );

        Utils.showError(
               context,
               exception
       );
    }

    public static void showError(final Activity activity,final Exception e ){
        if(Looper.myLooper() != Looper.getMainLooper() ){
            activity.runOnUiThread( () -> showError(activity , e ));
        }



        new AlertDialog.Builder(activity)
                .setTitle( R.string.error )
                .setMessage(e.getMessage())
                .setPositiveButton( android.R.string.ok,null)
                .show();
    }


    public static void wrapInReIfNecessary(Throwable t ) throws  RuntimeException{
        if( t instanceof  RuntimeException ){
            throw  ( RuntimeException ) t;
        }else {
            throw new RuntimeException( t );
        }
    }
}
