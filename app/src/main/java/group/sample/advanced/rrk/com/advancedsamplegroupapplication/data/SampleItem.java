package group.sample.advanced.rrk.com.advancedsamplegroupapplication.data;

import android.support.annotation.NonNull;

/**
 * @author KIM
 * @version 0.0.1
 * @date 2017-11-23
 * @since 0.0.1
 */

public class SampleItem {
    
    Class<?> clazz;
    String description;



    public SampleItem( @NonNull Class<?> clazz,@NonNull String description) {
        this.clazz = clazz;
        this.description = description;
    }

    public Class<?> getClazz() {
        return clazz;
    }

    public void setClazz(Class<?> clazz) {
        this.clazz = clazz;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
