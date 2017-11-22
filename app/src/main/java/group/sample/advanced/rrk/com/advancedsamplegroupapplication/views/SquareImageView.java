package group.sample.advanced.rrk.com.advancedsamplegroupapplication.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;

import group.sample.advanced.rrk.com.advancedsamplegroupapplication.R;

/**
 * @author KIM
 * @version 0.0.1
 * @date 2017-11-12
 * @since 0.0.1
 */

public class SquareImageView extends AppCompatImageView {

    /**
     *
     * @author KIM
     * @version 0.0.1
     */
    private Paint edgePaint;

    /**
     *
     */
    private int borderRadius;

    public SquareImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        // style tag로 정리하기 . 굳이

        init();
    }

    private void init(){

        int mAccentColor = ContextCompat.getColor( getContext() , R.color.colorAccent);

        borderRadius = (int )getResources().getDimension( R.dimen.circle_border_radius);
        edgePaint = new Paint();
        edgePaint.setAntiAlias( true );
        edgePaint.setStyle( Paint.Style.STROKE );
        edgePaint.setColor(mAccentColor);
        edgePaint.setStrokeWidth( borderRadius );

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (isActivated()) {
            int left = borderRadius;
            int top = borderRadius;
            int bottom = getMeasuredHeight() - borderRadius;
            int right = getMeasuredWidth() - borderRadius;
            canvas.drawRect(left, top, right, bottom, edgePaint);
        }
    }
}
