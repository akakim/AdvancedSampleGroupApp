package group.sample.advanced.rrk.com.advancedsamplegroupapplication.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.SweepGradient;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * @author KIM
 * @version 0.0.1
 * @date 2018-04-08
 * @since 0.0.1
 */

public class CircleGradient extends View {

    private static final int START_ANGLE_POINT = 0;

    private final        Paint paint;
    private final        RectF rect;

    private              float angle;


    private             Shader defaultShader;


    private             int [] colors = {
                                        Color.RED, Color.GREEN, Color.BLUE,
                                        Color.YELLOW, Color.WHITE };



    private             float [] pos = {
                                        0.0f, 0.25f, 0.5f,
                                        0.75f, 1.0f };


    public CircleGradient(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        final int strokeWidth = 40;





        paint = new Paint();
        paint.setAntiAlias( true );
        paint.setStyle( Paint.Style.STROKE );
        paint.setStrokeWidth( strokeWidth );

        defaultShader = new LinearGradient(0,0,200,200, colors, pos  , Shader.TileMode.CLAMP );

        paint.setShader( new SweepGradient( getWidth()/2,getHeight()/2,colors,pos ));

//        paint.set

        rect = new RectF(strokeWidth,strokeWidth,200 + strokeWidth , 200 + strokeWidth );


        // initial angle ( optional it can be zero )
        angle = 360;
    }

    /**
     *
     * @return
     */
    public Shader getDefaultShader() {
        return defaultShader;
    }


    /**
     *
     * @param tileMode tileMode .
     */
    public void setDefaultLinearShader( Shader.TileMode tileMode ) {
        this.defaultShader = new LinearGradient(100,100,200,200, colors, pos  , tileMode );
    }


    public void setDefaultSweepShader(Shader shader) {
        this.defaultShader = shader;

//        new SweepGradient( 100,100,colors,pos );
    }




    /**
     *
     * @param canvas
     */
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

//        canvas.draw
        canvas.drawArc( rect , START_ANGLE_POINT, angle , false , paint );
//        canvas.drawCircle(100,100,70,paint );
    }


    public float getAngle() {
        return angle;
    }

    public void setAngle(float angle) {
        this.angle = angle;
    }
}
