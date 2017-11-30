package group.sample.advanced.rrk.com.advancedsamplegroupapplication.samples;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.List;

import butterknife.BindViews;
import butterknife.ButterKnife;
import group.sample.advanced.rrk.com.advancedsamplegroupapplication.R;


//TODO : http://jakewharton.github.io/butterknife/ 코드를 응용하며 정리할 것.
public class ButterKnifeExampleActivity extends AppCompatActivity {


    @BindViews({ R.id.tvUserFirstName, R.id.tvUserLastName,R.id.tvUserInfo})
    List<TextView> userProfile;


    static final ButterKnife.Action<View> DISABLE = new ButterKnife.Action<View>() {
        @Override
        public void apply(@NonNull View view, int index) {
            view.setEnabled( false );
        }
    };

    static final ButterKnife.Action<TextView> INITTASK = new ButterKnife.Action<TextView>() {
        @Override
        public void apply(@NonNull TextView view, int index) {
            view.setText("initializeed... ");
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_butter_knife_example);
    }
}
