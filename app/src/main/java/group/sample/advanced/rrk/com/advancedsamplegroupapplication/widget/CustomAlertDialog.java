package group.sample.advanced.rrk.com.advancedsamplegroupapplication.widget;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import butterknife.ButterKnife;
import group.sample.advanced.rrk.com.advancedsamplegroupapplication.R;

/**
 * Created by RyoRyeong Kim on 2018-05-25.
 */

public class CustomAlertDialog extends AlertDialog {

    TextView tvTitle;
    TextView tvContent;
    public CustomAlertDialog(Context context) {
        super(context);
        View v = View.inflate( context , R.layout.custom_alert_layout,null);

        tvTitle = (TextView) v.findViewById( R.id.tvTitle);
        tvContent = (TextView) v.findViewById( R.id.tvContent);

        tvTitle.setText( " 알림 ");
        tvContent.setText( " 네트워크가 연결 되지 않앗습니다. ");
    }

    public CustomAlertDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    public CustomAlertDialog(Context context, int themeResId) {
        super(context, themeResId);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView( R.layout.custom_alert_layout);


        ButterKnife.bind(this );



//        Butter

    }

    public CustomAlertDialog setMessage(String title, String content ) {

       tvTitle.setText(title);
        tvContent.setText(content);

        return this;
    }
}
