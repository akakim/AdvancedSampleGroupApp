package group.sample.advanced.rrk.com.advancedsamplegroupapplication.samples.widget;

import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import group.sample.advanced.rrk.com.advancedsamplegroupapplication.R;

public class FabAndSnackBarActivity extends AppCompatActivity {

    @BindView(R.id.coordinatorLayout)
    CoordinatorLayout coordinatorLayout;

    @BindView(R.id.btnShowSnackBar)
    Button btnShowSnackBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fab_and_snack_bar);

        ButterKnife.bind(this);




    }


    @OnClick(R.id.btnShowSnackBar)
    void BtnShowSnackBarClick(){
        Snackbar.make( coordinatorLayout,
                "this is sample SnackBar",
                Snackbar.LENGTH_LONG)
                .setAction("CLOSE", v -> {
                    Toast.makeText(FabAndSnackBarActivity.this,"FabSna" +
                            "is Clicked",Toast.LENGTH_SHORT).show();
                })
                .show();
    }
}
