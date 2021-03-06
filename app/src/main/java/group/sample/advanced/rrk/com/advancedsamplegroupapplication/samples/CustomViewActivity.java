package group.sample.advanced.rrk.com.advancedsamplegroupapplication.samples;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import group.sample.advanced.rrk.com.advancedsamplegroupapplication.R;
import group.sample.advanced.rrk.com.advancedsamplegroupapplication.samples.fragment.CustomCircleFragment;

public class CustomViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView( R.layout.activity_custom_view);

        getSupportFragmentManager().beginTransaction().replace(
                R.id.sampleContainer,
                new CustomCircleFragment()
        ).commit();
    }
}
