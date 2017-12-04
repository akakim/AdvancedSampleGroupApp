package group.sample.advanced.rrk.com.advancedsamplegroupapplication.samples.charsample;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.SeekBar;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import group.sample.advanced.rrk.com.advancedsamplegroupapplication.R;
import group.sample.advanced.rrk.com.advancedsamplegroupapplication.samples.charsample.chartlib.custom.MonthAxisValueFormatter;
import group.sample.advanced.rrk.com.advancedsamplegroupapplication.samples.charsample.chartlib.custom.MyAxisValueFormatter;

public class SampleDayOfKW extends DemoBase implements OnChartValueSelectedListener {



    @BindView(R.id.chartPerMonth)
    BarChart chartPerMonth;

    ButterKnife.Action<BarChart> initAction = new ButterKnife.Action<BarChart>() {
        @Override
        public void apply(@NonNull BarChart view, int index) {


        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample_day_of_kw);

        ButterKnife.bind( this );


        chartPerMonth.setOnChartValueSelectedListener(this );
        chartPerMonth.setDrawBarShadow( false );

        chartPerMonth.getDescription().setEnabled(true);


        chartPerMonth.setPinchZoom(true);   // 뭐지 이건 ?

        chartPerMonth.setDrawGridBackground(true);


        // 먼저 X축에 관련된 설정을 한다.
        IAxisValueFormatter xAxisFormatter = new MonthAxisValueFormatter(chartPerMonth);

        XAxis xAxis = chartPerMonth.getXAxis();
        xAxis.setPosition( XAxis.XAxisPosition.BOTTOM );
        xAxis.setDrawGridLines(true);
        xAxis.setGranularity( 1f ); // only intervals of 1 day
        xAxis.setLabelCount(7 );
        xAxis.setValueFormatter( xAxisFormatter );


        IAxisValueFormatter custom = new MyAxisValueFormatter();

//        YAxis rightAxis = chartPerMonth.getAxisRight();
//        rightAxis.setDrawGridLines(false);
////        rightAxis.setTypeface(mTfLight);
//        rightAxis.setLabelCount(8, false);
//        rightAxis.setValueFormatter(chartPerMonth);
//        rightAxis.setSpaceTop(15f);
//        rightAxis.setAxisMinimum(0f); // this replaces setStartAtZero(true)
    }

    @Override
    public void onValueSelected(Entry e, Highlight h) {

    }

    @Override
    public void onNothingSelected() {

    }

    private void setData(int count,float range){
        float start = 1;
        ArrayList<BarEntry> yValue1 = new ArrayList<>();

//        for(int i = (int ) start ; i< start + count + 1 ; i++){
////            float mult =
//        }
    }
}
