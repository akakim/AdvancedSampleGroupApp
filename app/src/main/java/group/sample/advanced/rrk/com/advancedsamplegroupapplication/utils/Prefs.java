package group.sample.advanced.rrk.com.advancedsamplegroupapplication.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.Size;

/**
 * @author KIM
 * @version 0.0.1
 * @date 2017-11-17
 * @since 0.0.1
 */

@SuppressLint({"CommitPrefEdits","ApplySharedPref"})
public class Prefs {

    private Prefs(){}



    @Size(2)
    public static int [] imageSpacing(Context context){
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        return new int[]{
                prefs.getInt("image_spacing_horizontal", 0), prefs.getInt("image_spacing_vertical", 0),
        };
    }
    public static void imageSpacing(Context context,int horizontal, int vertical){
        PreferenceManager.getDefaultSharedPreferences(context)
                .edit()
                .putInt("image_spacing_horizontal",horizontal)
                .putInt("image_spacing_vertical",vertical)
                .commit();
    }
}
