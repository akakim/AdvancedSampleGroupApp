package group.sample.advanced.rrk.com.advancedsamplegroupapplication.samples.mvp;

/**
 * Created by RyoRyeong Kim on 2017-12-28.
 */

public class TaeWonImageItem {

    private int imageRes;
    private String title;

    public TaeWonImageItem(int imageRes, String title) {
        this.imageRes = imageRes;
        this.title = title;
    }

    public int getImageRes() {
        return imageRes;
    }

    public String getTitle() {
        return title;
    }
}
