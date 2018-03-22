package rrk.dev.andcodephilo.widget;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.support.annotation.DrawableRes;
import android.support.v7.widget.AppCompatSpinner;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.RelativeLayout;

import java.util.ArrayList;

import rrk.dev.andcodephilo.R;

/**
 * Created by RyoRyeong Kim on 2018-03-22.
 *
 *
 */

public class SpinnerTypeSelector extends RelativeLayout implements AdapterView.OnItemSelectedListener{

    public final static String notingSelectedKey = "";
    public final static String notingSelectedValue = "선택안함";


    private int defaultPadding = 7;                 // padding
    AppCompatSpinner appCompatSpinner;


    private final int MODE_DIALOG    = 0 ;      // referene appCompatSpinner
    private final int MODE_DROP_DOWN = 1 ;


    ArrayList<String> keyList = new ArrayList<>();
    ArrayList<String> valueList = new ArrayList<>();


    private int spinnerBackground = android.R.layout.simple_spinner_dropdown_item ;            // defaultSpinnerBackground

    public SpinnerTypeSelector(Context context) {
        this(context, null , -1 );
    }

    public SpinnerTypeSelector(Context context, AttributeSet attrs) {
        this(context, attrs , -1 );
    }

    public SpinnerTypeSelector(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);


        init ( context, attrs ,defStyleAttr );
    }

    private void init ( Context context, AttributeSet attrs,int defStyleAttr ){


        float density = context.getResources().getDisplayMetrics().density;


        LayoutParams layoutParams = new LayoutParams( ViewGroup.LayoutParams.MATCH_PARENT,
                                     ViewGroup.LayoutParams.MATCH_PARENT );


        int defaultMargin  = (int) density * defaultPadding ;


        TypedArray a = context.obtainStyledAttributes(attrs,R.styleable.SpinnerTypeSelector);

        defaultMargin = a.getInt( R.styleable.SpinnerTypeSelector_padding , defaultMargin );
        spinnerBackground = a.getInt( R.styleable.SpinnerTypeSelector_padding , android.R.layout.simple_spinner_dropdown_item );
        int mode = a.getInt( R.styleable.SpinnerTypeSelector_spinnerMode , MODE_DIALOG );

        appCompatSpinner = new AppCompatSpinner(context,attrs ,defStyleAttr,mode );
        layoutParams.setMargins( defaultMargin,defaultMargin,defaultMargin,defaultMargin );

        appCompatSpinner.setLayoutParams( layoutParams ) ;


        addView( appCompatSpinner );

    }

    public void setOnItemSelectedListner(AdapterView.OnItemSelectedListener onItemSelectedListener ){
        appCompatSpinner.setOnItemSelectedListener( onItemSelectedListener );
    }

    public void setData( ArrayList<String> keyList,ArrayList<String> valueList , AdapterView.OnItemSelectedListener onItemSelectedListener){

        this.keyList.clear();
        this.valueList.clear();
        this.keyList.addAll ( keyList );
        this.valueList.addAll( valueList );

        ArrayAdapter adapter = new ArrayAdapter(getContext(), spinnerBackground ,this.valueList );

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item );
        appCompatSpinner.setAdapter(adapter);
        appCompatSpinner.setOnItemSelectedListener( onItemSelectedListener );
    }


    public void triggerSelection( final int position , final ArrayList<String> keyList,final ArrayList<String> valueList , AdapterView.OnItemSelectedListener onItemSelectedListener ){

        setData( keyList,valueList,onItemSelectedListener);


        post(new Runnable() {
            @Override
            public void run() {
                appCompatSpinner.setPrompt(valueList.get(position));
            }
        });
    }
    public void setDefaultData( ArrayList<String> keyList,ArrayList<String> valueList, AdapterView.OnItemSelectedListener onItemSelectedListener){


        appCompatSpinner.setOnItemSelectedListener( onItemSelectedListener );
    }

    private int findSelection( ArrayList<String> keyList){

        int index = -1;


        return index;
    }

    public void setSelection(int position){

    }
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
