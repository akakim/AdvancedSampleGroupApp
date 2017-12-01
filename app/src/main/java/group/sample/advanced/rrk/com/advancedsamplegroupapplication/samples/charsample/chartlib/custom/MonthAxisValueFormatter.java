package group.sample.advanced.rrk.com.advancedsamplegroupapplication.samples.charsample.chartlib.custom;

import com.github.mikephil.charting.charts.BarLineChartBase;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;

/**
 * Created by RyoRyeong Kim on 2017-11-30.
 */

public class MonthAxisValueFormatter implements IAxisValueFormatter {

    protected String[] mMonths;

    private BarLineChartBase<?> chart;


    public MonthAxisValueFormatter(BarLineChartBase chart) {
        this.chart = chart;

        mMonths = new String[11];
        for(int k = 0; k< mMonths.length;k++){
            mMonths[k] = k + " 월 ";
        }
    }

    @Override
    public String getFormattedValue(float value, AxisBase axis) {

        int idx = (int) value;
        return mMonths[idx];
    }
}
