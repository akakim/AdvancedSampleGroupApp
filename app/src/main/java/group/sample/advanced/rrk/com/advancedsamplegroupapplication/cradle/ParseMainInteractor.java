package group.sample.advanced.rrk.com.advancedsamplegroupapplication.cradle;

/**
 * Created by RyoRyeong Kim on 2017-12-20.
 */

public interface ParseMainInteractor {

    interface OnFinishedListener{
        String onFinished();
    }

    void init(Runnable runnable);
}
