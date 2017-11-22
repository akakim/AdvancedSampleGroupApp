package group.sample.advanced.rrk.com.advancedsamplegroupapplication.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.ColorInt;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

/**
 * @author KIM
 * @version 0.0.1
 * @date 2017-11-12
 * @since 0.0.1
 */

public class LineView extends View {

    private Paint paint;


    public LineView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);


        this.paint = new Paint();
        this.paint.setAntiAlias( true );
        this.paint.setColor( Color.BLACK);
        this.paint.setStyle( Paint.Style.FILL_AND_STROKE);
        if(isInEditMode()) setWidth( 8 );

    }


    public void setWidth ( int width){
        width = ( int ) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                width,
                getResources().getDisplayMetrics()
        );

        invalidate();
    }

    public void setColor(@ColorInt int color){
        paint.setColor( color );
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if( getTag() != null && getTag().equals("1")){

            canvas.drawLine(
              0,
                    (getMeasuredHeight() / 2 ),
                    getMeasuredWidth(),
                    (getMeasuredHeight() / 2 ),
                    paint
            );
        } else {
            canvas.drawLine(
                    (getMeasuredWidth() / 2),
                    0,
                    (getMeasuredWidth() / 2),
                    getMeasuredHeight(),
                    paint
            );
        }
    }
}
