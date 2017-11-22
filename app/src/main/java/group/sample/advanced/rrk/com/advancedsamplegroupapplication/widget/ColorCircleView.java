package group.sample.advanced.rrk.com.advancedsamplegroupapplication.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.ColorInt;
import android.util.AttributeSet;
import android.view.View;

import java.util.jar.Attributes;

import group.sample.advanced.rrk.com.advancedsamplegroupapplication.R;

/**
 * @author KIM
 * @version 0.0.1
 * @date 2017-11-16
 * @since 0.0.1
 */

public class ColorCircleView extends View {

    private int circleRadius;
    private Paint edgePaint;
    private Paint fillPaint;


    public ColorCircleView(Context context) {
        super(context);
        init();
    }

    public ColorCircleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }
    public ColorCircleView(Context context, AttributeSet attributeSet, int defStyleAttr){
        super(context,attributeSet,defStyleAttr);
        init();
    }

    private void init(){
        setWillNotDraw(false);

        circleRadius = (int )getResources().getDimension(R.dimen.circle_border_radius);
        fillPaint = new Paint();
        fillPaint.setAntiAlias(true);
        fillPaint.setColor(Color.BLACK);

        edgePaint = new Paint();
        edgePaint.setAntiAlias(true);
        edgePaint.setStyle(Paint.Style.STROKE);
        edgePaint.setColor(Color.WHITE);
        edgePaint.setStrokeWidth(circleRadius);
    }

    public void setColor(@ColorInt int color) {
        fillPaint.setColor(color);
        invalidate();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(getMeasuredWidth(), getMeasuredWidth());
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        final int center = (getMeasuredWidth() / 2);
        final int radius = (getMeasuredWidth() / 2) - circleRadius;
        canvas.drawCircle(center, center, radius, fillPaint);
        canvas.drawCircle(center, center, radius, edgePaint);
    }
}
