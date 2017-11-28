package group.sample.advanced.rrk.com.advancedsamplegroupapplication.samples.widget;

import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import group.sample.advanced.rrk.com.advancedsamplegroupapplication.BaseActivity;
import group.sample.advanced.rrk.com.advancedsamplegroupapplication.R;

public class TextInputLayoutCustomActivity extends BaseActivity {


    @BindView(R.id.etID)
    TextInputEditText etID;

    @BindView(R.id.etPassword)
    TextInputEditText etPassword;

    @BindView(R.id.etAmount)
    TextInputEditText etAmount;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_input_layout_custom);

        ButterKnife.bind( this );


    }


    @OnClick(R.id.btnCommit)
    public void onClickCommit (){
        if("".equals(etID.getText().toString())){
            etID.setError(getString(R.string.errorID));
        }

        if("".equals(etPassword.getText().toString())){
            etPassword.setError(getString(R.string.errorPassword));
        }

        if("".equals(etAmount.getText().toString())){
            etAmount.setError(getString(R.string.errorAmount));
        }
    }
}
