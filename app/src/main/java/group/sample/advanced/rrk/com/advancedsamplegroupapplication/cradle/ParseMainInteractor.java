package group.sample.advanced.rrk.com.advancedsamplegroupapplication.cradle;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import org.json.JSONObject;

import java.util.Map;

/**
 * Created by RyoRyeong Kim on 2017-12-20.
 */

public interface ParseMainInteractor {

    interface OnFinishedListener{
        void onFinished(final boolean isError,String responseData,JSONObject data);
    }

    void init(AppCompatActivity activity);
    void initFragment(Fragment fragment);
}
