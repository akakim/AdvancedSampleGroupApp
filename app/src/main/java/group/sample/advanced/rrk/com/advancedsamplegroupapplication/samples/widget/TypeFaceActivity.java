package group.sample.advanced.rrk.com.advancedsamplegroupapplication.samples.widget;

import android.content.Context;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.TextView;

import com.tsengvn.typekit.Typekit;
import com.tsengvn.typekit.TypekitContextWrapper;

import butterknife.BindView;
import butterknife.ButterKnife;
import group.sample.advanced.rrk.com.advancedsamplegroupapplication.BaseActivity;
import group.sample.advanced.rrk.com.advancedsamplegroupapplication.R;


//import com.tsengvn.typekit.Typekit;
//import com.tsengvn.typekit.TypekitContextWrapper;


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

    @BindView(R.id.tvIsCustom)
    TextView tvIsCustom;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView( R.layout.activity_type_face);
        Log.d(TAG,"onCreate()");
        ButterKnife.bind(this);

//        tvTypeNormal = ( TextView ) findViewById( R.id.tvTypeNormal);
//        tvTypeBold = ( TextView ) findViewById( R.id.tvTypeBold);
//        tvIsNormal = ( TextView ) findViewById( R.id.tvIsNormal);

        Typeface typeface = Typeface.createFromAsset(getAssets(),"fonts/LGSmHaR_1130.ttf");
        Typeface typefaceBold = Typeface.createFromAsset(getAssets(),"fonts/LGSmHaSB.ttf");
        Typeface rixStar = Typeface.createFromAsset(getAssets(),"fonts/rix.ttf");

        Typekit.getInstance()
                .addNormal( rixStar )
                .addBold( typefaceBold );

        Typeface typeface1Normal = Typekit.getInstance().get(Typekit.Style.Normal);


        tvTypeNormal.setText(" 일반 폰트체 테스트 ");




//        tvTypeNormal.setTypeface(typeface);
        tvTypeBold.setText(" 굵은 글씨체 테스트  ");
//        tvTypeBold.setTypeface( typefaceBold );
        tvIsNormal.setText(" 일반 미적용 테스트 ");
        tvIsNormal.setTypeface( typeface1Normal);
//        tvIsNormal.setTypeface(rixStar);
        tvIsCustom.setText("다시적용 테스트 " );
        tvIsCustom.setTypeface( typeface1Normal );
    }
    /**
     * 기본 폰트 지정
     *
     * @param newBase
     */
    @Override
    protected void attachBaseContext( Context newBase ) {
//        super.attachBaseContext( newBase );
        Log.d(getClass().getSimpleName(),"onAttchBaseContext");
        super.attachBaseContext( TypekitContextWrapper.wrap( newBase ) );

//        if( tvTypeNormal.getTypeface() ==null ){
//            Log.e(TAG,"tvTypeNormal is null" );
//        }else {
//            int style = tvTypeNormal.getTypeface().getStyle();
//            Log.d(TAG,"tvTypeNormal style id " + style);
//        }



    }


    @Override
    protected void onStart() {
        super.onStart();
        Log.d(getClass().getSimpleName(),"onStart");
        tvTypeNormal.setText(" 일반 폰트체 테스트 ");
//        Log.d(TAG,"get tvTypeNormal ID () " + tvTypeNormal.getId());
//        Log.d(TAG,"get tvTypeBold ID () " + tvTypeBold.getId());
//        Log.d(TAG,"get tvIsNormal ID () " + tvIsNormal.getId());
//        tvTypeNormal.setTypeface(typeface);
        tvTypeBold.setText(" 굵은 글씨체 테스트  ");
        tvIsNormal.setText(" 일반 미적용 테스트 ");

        if( tvTypeNormal.getTypeface() ==null ){
            Log.e(TAG,"tvTypeNormal is null" );
        }else {
            int style = tvTypeNormal.getTypeface().getStyle();
            Log.d(TAG,"tvTypeNormal style id " + style);
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(getClass().getSimpleName(),"onResume");

        Typeface typeface1Normal = Typekit.getInstance().get(Typekit.Style.Normal);
        Typeface typeface1Bold = Typekit.getInstance().get(Typekit.Style.Bold);
        int typefaceNormalStyle= typeface1Normal.getStyle();
        int typefaceBoldStyle= typeface1Bold.getStyle();

//        int getNormalstyle =  tvTypeNormal.getTypeface().getStyle();
//        int getBoldstyle = tvTypeBold.getTypeface().getStyle();

        Log.d(TAG,"Typekit Normal " + typefaceNormalStyle);
//        Log.d(TAG,"tvTypeNormal Style  " +  getNormalstyle);

        Log.d(TAG,"Typekit Bold " + typefaceBoldStyle);
//        Log.d(TAG,"tvTypeBold Style  " +  getBoldstyle);
//        tvTypeNormal.getTypeface().getStyle();




//        tvTypeNormal.setTypeface(typeface);


    }
}
