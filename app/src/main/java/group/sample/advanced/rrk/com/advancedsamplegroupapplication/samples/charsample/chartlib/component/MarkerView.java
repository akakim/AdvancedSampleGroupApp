package group.sample.advanced.rrk.com.advancedsamplegroupapplication.samples.charsample.chartlib.component;

import android.content.Context;
import android.graphics.Canvas;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;

import com.github.mikephil.charting.charts.Chart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.highlight.Highlight;

import java.lang.ref.WeakReference;

import group.sample.advanced.rrk.com.advancedsamplegroupapplication.samples.charsample.chartlib.utils.MPPointF;

/**
 * Created by RyoRyeong Kim on 2017-11-30.
 */

public class MarkerView extends RelativeLayout implements com.github.mikephil.charting.components.IMarker {

    private  com.github.mikephil.charting.utils.MPPointF mOffset = new  com.github.mikephil.charting.utils.MPPointF();
    private  com.github.mikephil.charting.utils.MPPointF mOffset2 = new  com.github.mikephil.charting.utils.MPPointF();
    private WeakReference<Chart> mWeakChart;

    public MarkerView(Context context,int layoutResource) {
        super(context);

        setupLayoutResource(layoutResource);
    }

    /**
     * Sets the layout resource for a custom MarkerView.
     *
     * @param layoutResource
     */
    private void setupLayoutResource(int layoutResource) {

        View inflated = LayoutInflater.from(getContext()).inflate(layoutResource, this);

        inflated.setLayoutParams(new LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT));
        inflated.measure(MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED), MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED));

        // measure(getWidth(), getHeight());
        inflated.layout(0, 0, inflated.getMeasuredWidth(), inflated.getMeasuredHeight());
    }

    public void setOffset( com.github.mikephil.charting.utils.MPPointF offset) {
        mOffset = offset;

        if (mOffset == null) {
            mOffset = new  com.github.mikephil.charting.utils.MPPointF();
        }
    }

    public void setOffset(float offsetX, float offsetY) {
        mOffset.x = offsetX;
        mOffset.y = offsetY;
    }



    public void setChartView(Chart chart) {
        mWeakChart = new WeakReference<>(chart);
    }

    public Chart getChartView() {
        return mWeakChart == null ? null : mWeakChart.get();
    }


    /**
     * implemnt IMaker
     * @return
     */
    @Override
    public com.github.mikephil.charting.utils.MPPointF getOffset() {
        return mOffset;
    }

    @Override
    public com.github.mikephil.charting.utils.MPPointF getOffsetForDrawingAtPoint(float posX, float posY) {
        com.github.mikephil.charting.utils.MPPointF offset = getOffset();
        mOffset2.x = offset.x;
        mOffset2.y = offset.y;

        Chart chart = getChartView();

        float width = getWidth();
        float height = getHeight();

        if (posX + mOffset2.x < 0) {
            mOffset2.x = - posX;
        } else if (chart != null && posX + width + mOffset2.x > chart.getWidth()) {
            mOffset2.x = chart.getWidth() - posX - width;
        }

        if (posY + mOffset2.y < 0) {
            mOffset2.y = - posY;
        } else if (chart != null && posY + height + mOffset2.y > chart.getHeight()) {
            mOffset2.y = chart.getHeight() - posY - height;
        }

        return mOffset2;
    }

    @Override
    public void refreshContent(Entry e, Highlight highlight) {
        measure(MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED),
                MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED));
        layout(0, 0, getMeasuredWidth(), getMeasuredHeight());
    }

    @Override
    public void draw(Canvas canvas, float posX, float posY) {

        com.github.mikephil.charting.utils.MPPointF offset = getOffsetForDrawingAtPoint(posX, posY);

        int saveId = canvas.save();
        // translate to the correct position and draw
        canvas.translate(posX + offset.x, posY + offset.y);
        draw(canvas);
        canvas.restoreToCount(saveId);
    }
}
