package rrk.dev.andcodephilo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by RyoRyeong Kim on 2018-02-08.
 */

public class StaticBroadCast extends BroadcastReceiver {


    @Override
    public void onReceive(Context context, Intent intent) {

        String action = intent.getAction();
        switch (action){
            case GlobalActionConstant.ACTION_BACKGROUND:
                break;
            case GlobalActionConstant.ACTION_FOREGROUND:
                break;

            /**
             * window Popup을 띄운다.
             */
            case GlobalActionConstant.ACTION_NOTIFICATION:
                WindowPopupManager.getInstance(context).show();
                break;
        }
    }
}
