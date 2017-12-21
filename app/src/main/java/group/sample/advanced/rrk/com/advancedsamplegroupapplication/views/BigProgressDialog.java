package group.sample.advanced.rrk.com.advancedsamplegroupapplication.views;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Window;
import android.widget.ProgressBar;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import group.sample.advanced.rrk.com.advancedsamplegroupapplication.R;

/**
 * Created by RyoRyeong Kim on 2017-12-20.
 */

public class BigProgressDialog extends Dialog {

    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    @BindView(R.id.tvContent)
    TextView tvContent;

    OnCancelListener onCancelListener;
    public BigProgressDialog(Context context) {
        this ( context,false,null);
    }

    public BigProgressDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.big_progress_dialog_layout);

        ButterKnife.bind(this );


        getWindow().setBackgroundDrawable( new ColorDrawable(Color.TRANSPARENT));
        setCancelable(cancelable);
    }



    @Override
    public void show() {
        super.show();
        progressBar.animate();
    }

    public void setContent(String msg){
        tvContent.setText(msg);
    }

    @Override
    public void dismiss() {
        super.dismiss();

    }
}
