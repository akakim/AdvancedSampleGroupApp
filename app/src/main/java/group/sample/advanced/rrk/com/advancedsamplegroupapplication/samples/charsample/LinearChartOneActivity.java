package group.sample.advanced.rrk.com.advancedsamplegroupapplication.samples.charsample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.SeekBar;
import android.widget.TextView;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.ChartTouchListener;
import com.github.mikephil.charting.listener.OnChartGestureListener;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import group.sample.advanced.rrk.com.advancedsamplegroupapplication.R;
import group.sample.advanced.rrk.com.advancedsamplegroupapplication.samples.charsample.chartlib.component.MarkerView;
import group.sample.advanced.rrk.com.advancedsamplegroupapplication.samples.charsample.chartlib.custom.CustomMakerView;

public class LinearChartOneActivity extends DemoBase implements SeekBar.OnSeekBarChangeListener,
        OnChartGestureListener, OnChartValueSelectedListener {


    @BindView(R.id.chart1)
    LineChart chart;

    @BindView( R.id.seekBar1)
    SeekBar mSeekBarX;

    @BindView( R.id.seekBar2)
    SeekBar mSeekBarY;

    @BindView( R.id.tvXMax)
    TextView tvXMax;

    @BindView( R.id.tvYMax)
    TextView tvYMax;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_linear_chart_one);

        ButterKnife.bind( this );



        mSeekBarX.setProgress(45);
        mSeekBarY.setProgress(100);

        mSeekBarY.setOnSeekBarChangeListener(this);
        mSeekBarX.setOnSeekBarChangeListener(this);

        chart.setOnChartGestureListener(this);
        chart.setOnChartValueSelectedListener(this);
        chart.setDrawGridBackground(false);

        // no description text
        chart.getDescription().setEnabled(false);

        // enable touch gestures
        chart.setTouchEnabled(true);

        // enable scaling and dragging
        chart.setDragEnabled(true);
        chart.setScaleEnabled(true);
        // mChart.setScaleXEnabled(true);
        // mChart.setScaleYEnabled(true);

        // if disabled, scaling can be done on x- and y-axis separately
        chart.setPinchZoom(true);

        // set an alternative background color
        // mChart.setBackgroundColor(Color.GRAY);

        // create a custom MarkerView (extend MarkerView) and specify the layout
        // to use for it
//        MarkerView markerView = new MarkerView(this , R.layout.layout_marker);
//        markerView.setChartView(chart);
//        chart.setMarker(markerView); -- > Customize?


        CustomMakerView customMakerView = new CustomMakerView( this ,R.layout.layout_marker);
        customMakerView.setChartView( chart );
        chart . setMarker( customMakerView );// 서로 인터페이스로 엮는거같다.

        // x - axis limit line
        LimitLine  llXAxis = new LimitLine( 10f, "index 10 ");
        llXAxis.setLineWidth( 4f );
        llXAxis.setLineWidth(4f);
        llXAxis.enableDashedLine(10f, 10f, 0f);
        llXAxis.setLabelPosition(LimitLine.LimitLabelPosition.RIGHT_BOTTOM);
        llXAxis.setTextSize(10f);

        XAxis xAxis = chart.getXAxis();
        xAxis.enableGridDashedLine(10f, 10f, 0f);

    }


    private void setData(int count,float range){
        ArrayList<Entry> values = new ArrayList<>();

        for (int i = 0;i < count; i++){
//            float val ( )
        }
    }
    /**
     * implement SeekBar
     * @param seekBar
     * @param progress
     * @param fromUser
     */
    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

    }

    /**
     * implement SeekBar
     * @param seekBar
     */

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    /**
     * implement SeekBar
     * @param seekBar
     */
    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }


    @Override
    public void onChartGestureStart(MotionEvent me, ChartTouchListener.ChartGesture lastPerformedGesture) {

    }

    @Override
    public void onChartGestureEnd(MotionEvent me, ChartTouchListener.ChartGesture lastPerformedGesture) {

    }

    @Override
    public void onChartLongPressed(MotionEvent me) {

    }

    @Override
    public void onChartDoubleTapped(MotionEvent me) {

    }

    @Override
    public void onChartSingleTapped(MotionEvent me) {

    }

    @Override
    public void onChartFling(MotionEvent me1, MotionEvent me2, float velocityX, float velocityY) {

    }

    @Override
    public void onChartScale(MotionEvent me, float scaleX, float scaleY) {

    }

    @Override
    public void onChartTranslate(MotionEvent me, float dX, float dY) {

    }

    /**
     * OnChartValueSelectedListener implement
     * @param e
     * @param h
     */
    @Override
    public void onValueSelected(Entry e, Highlight h) {
        Log.i("Entry selected", e.toString());
        Log.i("LOWHIGH", "low: " + chart.getLowestVisibleX() + ", high: " + chart.getHighestVisibleX());
        Log.i("MIN MAX", "xmin: " + chart.getXChartMin() + ", xmax: " + chart.getXChartMax() + ", ymin: " + chart.getYChartMin() + ", ymax: " + chart.getYChartMax());
    }

    /**
     * OnChartValueSelectedListener implement
     */
    @Override
    public void onNothingSelected() {
        Log.i("Nothing selected", "Nothing selected.");
    }
}
