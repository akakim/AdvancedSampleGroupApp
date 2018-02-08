package rrk.dev.andcodephilo;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by RyoRyeong Kim on 2018-02-08.
 */

public class NetworkUtil {

    public static boolean isNetworkOn( Context context){


        boolean isNetworkOn = false;
        ConnectivityManager connectivityManager  = ( ConnectivityManager ) context.getSystemService( Context.CONNECTIVITY_SERVICE );

        NetworkInfo network = connectivityManager.getActiveNetworkInfo();

        if( network == null ){
            return isNetworkOn;
        }else {
            if( network.getType() == ConnectivityManager.TYPE_WIFI ){

                isNetworkOn = true;
            } else if (network.getType() == ConnectivityManager.TYPE_MOBILE  ) {

                isNetworkOn = true;
            }


            return isNetworkOn;
        }
    }
}
