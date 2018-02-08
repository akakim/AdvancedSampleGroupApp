package rrk.dev.andcodephilo;

import android.app.AlertDialog;
import android.content.Context;
import android.view.View;

/**
 * Created by RyoRyeong Kim on 2018-02-08.
 */

public class BasicDialog extends AlertDialog {
    protected BasicDialog(Context context) {
        super(context);
        init(context,-1);
    }

    protected BasicDialog(Context context, int themeResId) {
        super(context, themeResId);
        init(context,themeResId);
    }

    public interface OnListener{
        void onDone(BasicDialog dlg);
        void onDismiss( BasicDialog dlg );
    }

    private OnListener onListener;


    private void init(Context context, int themeResId){

        if( themeResId == -1 ){
//            View v = View.inflate( context, R.layout.dlg_done, null );

        }
    }


}
