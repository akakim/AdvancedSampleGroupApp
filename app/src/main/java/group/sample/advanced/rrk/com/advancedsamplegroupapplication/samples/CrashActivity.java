package group.sample.advanced.rrk.com.advancedsamplegroupapplication.samples;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

import com.crashlytics.android.Crashlytics;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import group.sample.advanced.rrk.com.advancedsamplegroupapplication.R;
import io.fabric.sdk.android.Fabric;

public class CrashActivity extends AppCompatActivity {

    @BindView(R.id.btnFabricCrash)
    Button btnFabricCrash;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crash);
        Fabric.with(this, new Crashlytics());
        ButterKnife.bind(this);

    }

    @OnClick(R.id.btnFabricCrash)
    public void fabricCrash(){


        throw new RuntimeException("runtime Fabric Log.");
    }
}
