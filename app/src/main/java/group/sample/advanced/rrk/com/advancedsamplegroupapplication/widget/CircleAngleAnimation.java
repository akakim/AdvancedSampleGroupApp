package group.sample.advanced.rrk.com.advancedsamplegroupapplication.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.animation.Animation;
import android.view.animation.Transformation;

/**
 * @author KIM
 * @version 0.0.1
 * @date 2018-04-08
 * @since 0.0.1
 */

public class CircleAngleAnimation extends Animation {

    CircleGradient circleGradient;

    private float oldAngle;
    private float newAngle;

    public CircleAngleAnimation(CircleGradient circleGradient, float newAngle) {
        this.circleGradient = circleGradient;
        this.newAngle = newAngle;

        oldAngle = this.circleGradient.getAngle();
    }

    public CircleAngleAnimation(Context context, AttributeSet attrs, CircleGradient circleGradient, float newAngle) {
        super(context, attrs);
        this.circleGradient = circleGradient;
        this.newAngle = newAngle;

        oldAngle = this.circleGradient.getAngle();
    }



    @Override
    protected void applyTransformation(float interpolatedTime, Transformation t) {
        super.applyTransformation(interpolatedTime, t);

        float angle = oldAngle + ( (newAngle - oldAngle ) * interpolatedTime );

        circleGradient.setAngle( angle );
        circleGradient.requestLayout();
    }
}
