package group.sample.advanced.rrk.com.advancedsamplegroupapplication.data;

import android.support.annotation.NonNull;

/**
 * Created by RyoRyeong Kim on 2018-01-24.
 */

public class SingleChoice {
    String content;
    public SingleChoice(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
