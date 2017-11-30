package group.sample.advanced.rrk.com.advancedsamplegroupapplication.samples.charsample.chartlib.custom;


import android.content.Context;
import android.widget.TextView;

import com.github.mikephil.charting.components.MarkerView;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.utils.MPPointF;

import java.text.DecimalFormat;

import butterknife.BindView;
import butterknife.ButterKnife;
import group.sample.advanced.rrk.com.advancedsamplegroupapplication.R;

/**
 * Created by RyoRyeong Kim on 2017-11-30.
 */

public class XYMarkerView extends MarkerView {


    @BindView(R.id.tvContent)
    TextView tvContent;
    private IAxisValueFormatter mAxisValueFormatter;

    private DecimalFormat format;


    /**
     * Constructor. Sets up the MarkerView with a custom layout resource.
     *
     * @param context
     */


    public XYMarkerView(Context context, IAxisValueFormatter mAxisValueFormatter) {
        super(context, R.layout.layout_marker);
        this.mAxisValueFormatter = mAxisValueFormatter;
        ButterKnife.bind(this);
        format = new DecimalFormat("###.0");
    }

    @Override
    public void refreshContent(Entry e, Highlight highlight) {


        tvContent.setText(" x : " + mAxisValueFormatter.getFormattedValue(e.getX(),null) + " , y " + format.format(e.getY()));

        super.refreshContent(e, highlight);

    }

    @Override
    public MPPointF getOffset() {
        return new MPPointF(-(getWidth() / 2), -getHeight());
    }
}
