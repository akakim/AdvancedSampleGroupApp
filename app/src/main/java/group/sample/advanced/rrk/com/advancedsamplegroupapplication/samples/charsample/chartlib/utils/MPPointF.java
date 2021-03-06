package group.sample.advanced.rrk.com.advancedsamplegroupapplication.samples.charsample.chartlib.utils;

/**
 * Created by RyoRyeong Kim on 2017-11-30.
 */

public class MPPointF extends ObjectPool.Poolable {

    private static ObjectPool<MPPointF> pool;

    public float x;
    public float y;

    static{
        pool = ObjectPool.create( 32, new MPPointF(0,0));
        pool.setReplenishPercentage(0.5f);
    }

    public MPPointF() {
    }

    public MPPointF(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public static MPPointF getInstance(float x, float y) {
        MPPointF result = pool.get();
        result.x = x;
        result.y = y;
        return result;
    }

    public static MPPointF getInstance() {
        return pool.get();
    }

    public static MPPointF getInstance(MPPointF copy) {
        MPPointF result = pool.get();
        result.x = copy.x;
        result.y = copy.y;
        return result;
    }


    @Override
    protected ObjectPool.Poolable instantiate() {
        return null;
    }
}
