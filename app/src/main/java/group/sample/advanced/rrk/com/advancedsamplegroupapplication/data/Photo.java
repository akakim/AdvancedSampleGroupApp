package group.sample.advanced.rrk.com.advancedsamplegroupapplication.data;

import android.net.Uri;
import android.support.annotation.NonNull;

import java.io.Serializable;

import group.sample.advanced.rrk.com.advancedsamplegroupapplication.inquiry.annotation.Column;
/**
 *
 * @author KIM
 * @version 0.0.1
 */

public class Photo implements Serializable{


    @Column public long _id;
    @Column public String _data;
    @Column public long datataken;

    public Photo(){}

    public Photo(Uri uri) {
        _data = uri.toString();
    }

    @NonNull
    public Uri getUri() {
        Uri uri = Uri.parse(_data);
        if (!uri.toString().startsWith("file://") && !uri.toString().startsWith("content://"))
            uri = Uri.parse(String.format("file://%s", uri.toString()));
        return uri;
    }

}
