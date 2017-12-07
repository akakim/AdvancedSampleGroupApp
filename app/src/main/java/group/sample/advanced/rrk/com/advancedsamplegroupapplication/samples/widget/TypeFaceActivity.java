package group.sample.advanced.rrk.com.advancedsamplegroupapplication.samples.widget;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import group.sample.advanced.rrk.com.advancedsamplegroupapplication.BaseActivity;
import group.sample.advanced.rrk.com.advancedsamplegroupapplication.R;


import com.tsengvn.typekit.Typekit;
import com.tsengvn.typekit.TypekitContextWrapper;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by RyoRyeong Kim on 2017-12-07.
 */

public class TypeFaceActivity extends BaseActivity {

    String TAG = getClass().getSimpleName();

    @BindView(R.id.tvTypeNormal)
    TextView tvTypeNormal;
//
    @BindView(R.id.tvTypeBold)
    TextView tvTypeBold;
//
    @BindView(R.id.tvIsNormal)
    TextView tvIsNormal;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView( R.layout.activity_type_face);
        ButterKnife.bind(this);

//        tvTypeNormal = ( TextView ) findViewById( R.id.tvTypeNormal);
//        tvTypeBold = ( TextView ) findViewById( R.id.tvTypeBold);
//        tvIsNormal = ( TextView ) findViewById( R.id.tvIsNormal);



//        Typekit.getInstance()
//                .addNormal( Typekit.createFromAsset( this, "fonts/rix.ttf" ) )
//                .addBold( Typekit.createFromAsset( this, "fonts/LGSmHaSB.ttf" ) );

        Typeface typeface = Typeface.createFromAsset(getAssets(),"fonts/LGSmHaR_1130.ttf");
        Typeface typefaceBold = Typeface.createFromAsset(getAssets(),"fonts/LGSmHaSB.ttf");
        Typeface rixStar = Typeface.createFromAsset(getAssets(),"fonts/rix.ttf");

        tvTypeNormal.setText(" 일반 폰트체 테스트 ");
        tvTypeNormal.setTypeface(typeface);
        tvTypeBold.setText(" 굵은 글씨체 테스트  ");
        tvTypeBold.setTypeface( typefaceBold );
        tvIsNormal.setText(" 일반 미적용 테스트 ");
        tvIsNormal.setTypeface(rixStar);

    }
    /**
     * 기본 폰트 지정
     *
     * @param newBase
     */
    @Override
    protected void attachBaseContext( Context newBase ) {
        super.attachBaseContext( newBase );
        Log.d(getClass().getSimpleName(),"onAttchBaseContext");
//        super.attachBaseContext( TypekitContextWrapper.wrap( newBase ) );

    }


    @Override
    protected void onStart() {
        super.onStart();
        Log.d(getClass().getSimpleName(),"onStart");
        tvTypeNormal.setText(" 일반 폰트체 테스트 ");
        Log.d(TAG,"get tvTypeNormal ID () " + tvTypeNormal.getId());
        Log.d(TAG,"get tvTypeBold ID () " + tvTypeBold.getId());
        Log.d(TAG,"get tvIsNormal ID () " + tvIsNormal.getId());
//        tvTypeNormal.setTypeface(typeface);
        tvTypeBold.setText(" 굵은 글씨체 테스트  ");
        tvIsNormal.setText(" 일반 미적용 테스트 ");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(getClass().getSimpleName(),"onResume");
    }
}
