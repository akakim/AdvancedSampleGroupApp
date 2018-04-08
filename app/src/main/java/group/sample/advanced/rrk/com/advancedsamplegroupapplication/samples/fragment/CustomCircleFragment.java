package group.sample.advanced.rrk.com.advancedsamplegroupapplication.samples.fragment;

import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Shader;
import android.graphics.SweepGradient;
import android.os.Bundle;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import group.sample.advanced.rrk.com.advancedsamplegroupapplication.R;
import group.sample.advanced.rrk.com.advancedsamplegroupapplication.adapter.sampleadapter.CustomCircleAdapter;
import group.sample.advanced.rrk.com.advancedsamplegroupapplication.data.GradientData;
import group.sample.advanced.rrk.com.advancedsamplegroupapplication.widget.CircleGradient;
import group.sample.advanced.rrk.com.advancedsamplegroupapplication.widget.CircleAngleAnimation;


public class CustomCircleFragment extends Fragment implements CustomCircleAdapter.OnClickStartAnimation{


//    @BindViews( { R.id.circle1 , R.id.circle2 , R.id.circle3,R.id.circle4,R.id.circle5 })
//    public List<CircleGradient> circleGradientList;
    @BindView( R.id.circle1)
    CircleGradient circle1;


//    @BindView(R.id.rvSampleList)
//    RecyclerView rvSampleList;

//    CustomCircleAdapter adapter;

    ArrayList<GradientData> gradientDataArrayList = new ArrayList<>();


    public CustomCircleFragment() {
        // Required empty public constructor
    }


    public static CustomCircleFragment newInstance(String param1, String param2) {
        CustomCircleFragment fragment = new CustomCircleFragment();
//        Bundle args = new Bundle();
//        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        if (getArguments() != null) {
//            mParam1 = getArguments().getString(ARG_PARAM1);
//            mParam2 = getArguments().getString(ARG_PARAM2);
//        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_custom_circle, container, false);

        ButterKnife.bind( this , view );

        int [] colors = {
                Color.RED, Color.GREEN, Color.BLUE,
                Color.YELLOW, Color.WHITE };



        float [] pos = {
                0.0f, 0.25f, 0.5f,
                0.75f, 1.0f };

        gradientDataArrayList.add( new GradientData( "리니어 Gradient Clamp ", new LinearGradient(0,0,200,200, colors, pos, Shader.TileMode.CLAMP)));
        gradientDataArrayList.add( new GradientData( "리니어 Gradient Mirror", new LinearGradient(0,0,200,200, colors, pos, Shader.TileMode.MIRROR)));
        gradientDataArrayList.add( new GradientData( "리니어 Gradient Repeat", new LinearGradient(0,0,200,200, colors, pos, Shader.TileMode.REPEAT)));
        gradientDataArrayList.add( new GradientData( "SweepGradient", new SweepGradient(0,0, colors ,pos)));



        ButterKnife.apply(circle1, new ButterKnife.Action<CircleGradient>() {
            @Override
            public void apply(@NonNull CircleGradient view, int index) {
                CircleAngleAnimation animation = new CircleAngleAnimation(view,240);
                animation.setDuration( 1000 );
                view.startAnimation( animation );
            }
        });

//        adapter = new CustomCircleAdapter( getContext(), gradientDataArrayList ,this );
//        ButterKnife.apply(circle, new ButterKnife.Action<Circle>() {
//            @Override
//            public void apply(@NonNull Circle view, int index) {
//                CircleAngleAnimation animation = new CircleAngleAnimation(circle,240);
//                animation.setDuration( 1000 );
//                circle.startAnimation( animation );
//            }
//        });

//        ButterKnife.apply(circleGradientList, new ButterKnife.Action<CircleGradient>() {
//            @Override
//            public void apply(@NonNull CircleGradient view, int index) {
//
//                switch ( index ){
//                    case 0:
//                        view.setDefaultSweepShader(  new LinearGradient(0,0,200,200, colors, pos, Shader.TileMode.CLAMP));
//                        break;
//                    case 1:
//                        view.setDefaultSweepShader(  new LinearGradient(0,0,200,200, colors, pos, Shader.TileMode.MIRROR));
//                        break;
//                    case 2:
//                        view.setDefaultSweepShader(  new LinearGradient(0,0,200,200, colors, pos, Shader.TileMode.REPEAT));
//                        break;
//                    case 3:
//                        view.setDefaultSweepShader(  new SweepGradient(0,0, colors ,pos) );
//                        break;
//                    default:
//                        view.setDefaultSweepShader(  new SweepGradient(0,0, colors ,pos) );
//                        break;
//                }
//
//                view.setOnClickListener( new View.OnClickListener(){
//
//                    @Override
//                    public void onClick(View v) {
//
//                        CircleAngleAnimation animation = new CircleAngleAnimation(view,240);
//                        animation.setDuration( 1000 * (index + 1 )  );
//                        view.startAnimation( animation );
//
////                        v.getAnimation().star();
//                    }
//                });
//                CircleAngleAnimation animation = new CircleAngleAnimation(view,240);
//                animation.setDuration( 1000 * (index + 1 )  );
//                view.startAnimation( animation );
//            }
//        });


//        rvSampleList.setAdapter( adapter );
//        rvSampleList.setLayoutManager(new GridLayoutManager( getContext() ,2));
        return view ;
    }

    @Override
    public void onClick(CircleGradient view) {


        CircleAngleAnimation animation = new CircleAngleAnimation(view ,240);

        animation.setDuration( 2000  );

        animation.startNow();
//        ani
//
//        CircleAngleAnimation animation = new CircleAngleAnimation(view,240);
//
//        animation.setInterpolator( new AccelerateDecelerateInterpolator());
//        animation.startNow();
//        view.getAnimation().startNow();
//
//        animation.setDuration( 2000  );
//
//
//        view.startAnimation( animation );

//        view.postInvalidateOnAnimation();
//        view.requestLayout();
//        view.start
    }
}
