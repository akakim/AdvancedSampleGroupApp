package group.sample.advanced.rrk.com.advancedsamplegroupapplication.widget;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;

/**
 * Created by RyoRyeong Kim on 2018-01-31.
 */

public class CustomScrollViewPager extends ViewPager {
    public CustomScrollViewPager(Context context) {
        super(context);
    }

    public CustomScrollViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}
