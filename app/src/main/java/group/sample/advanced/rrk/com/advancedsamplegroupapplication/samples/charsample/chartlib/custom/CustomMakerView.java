package group.sample.advanced.rrk.com.advancedsamplegroupapplication.samples.charsample.chartlib.custom;

import android.content.Context;
import android.widget.TextView;

import com.github.mikephil.charting.data.CandleEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.utils.MPPointF;
import com.github.mikephil.charting.utils.Utils;

import butterknife.BindView;
import butterknife.ButterKnife;
import group.sample.advanced.rrk.com.advancedsamplegroupapplication.R;
import group.sample.advanced.rrk.com.advancedsamplegroupapplication.samples.charsample.chartlib.component.MarkerView;

/**
 * Created by RyoRyeong Kim on 2017-11-30.
 */

public class CustomMakerView extends MarkerView {

    @BindView(R.id.tvContent)
    TextView tvContent;

    public CustomMakerView(Context context, int layoutResource) {
        super(context, layoutResource);
        ButterKnife.bind(this );
    }

    @Override
    public void refreshContent(Entry e, Highlight highlight) {

        if( e instanceof CandleEntry ){
            CandleEntry entry = ( CandleEntry ) e;

            tvContent.setText(Utils.formatNumber(entry.getHigh(),0,true));

        }else {
            tvContent.setText(Utils.formatNumber(e.getY(),0,true));
        }
        super.refreshContent(e, highlight);
    }

    @Override
    public MPPointF getOffset() {
        return new MPPointF(-(getWidth() / 2), -getHeight());
    }

}
