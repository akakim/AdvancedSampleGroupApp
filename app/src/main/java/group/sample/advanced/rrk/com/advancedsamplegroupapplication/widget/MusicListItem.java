package group.sample.advanced.rrk.com.advancedsamplegroupapplication.widget;


/**
 * Created by RyoRyeong Kim on 2018-01-31.
 */

class MusicListItem {
    private static final MusicListItem ourInstance = new MusicListItem();

    static MusicListItem getInstance() {
        return ourInstance;
    }

    private MusicListItem() {
    }
}
