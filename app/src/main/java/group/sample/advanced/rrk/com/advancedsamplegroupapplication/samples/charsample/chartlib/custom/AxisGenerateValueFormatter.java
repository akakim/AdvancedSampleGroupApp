package group.sample.advanced.rrk.com.advancedsamplegroupapplication.samples.charsample.chartlib.custom;

import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;

import java.text.DecimalFormat;

/**
 * Created by RyoRyeong Kim on 2017-12-01.
 */

public class AxisGenerateValueFormatter implements IAxisValueFormatter {

    private DecimalFormat decimalFormat;

    AxisGenerateValueFormatter(){
        decimalFormat = new DecimalFormat("###,###,###,##0.0");
    }


    @Override
    public String getFormattedValue(float value, AxisBase axis) {
        return decimalFormat.format(value) +  " kw ";
    }
}
