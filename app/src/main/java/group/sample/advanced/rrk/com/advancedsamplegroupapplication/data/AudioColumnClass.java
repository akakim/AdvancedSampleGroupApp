package group.sample.advanced.rrk.com.advancedsamplegroupapplication.data;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import android.provider.MediaStore;

/**
 * @author KIM
 * @version 0.0.1
 * @date 2017-11-25
 * @since 0.0.1
 */

public class AudioColumnClass implements Parcelable{
//    MediaStore.Audio.Media.ALBUM,
//    MediaStore.Audio.Media.ARTIST,
//    MediaStore.Audio.Media.TITLE,
//    MediaStore.Audio.Media.IS_MUSIC,
//    MediaStore.Audio.Media.DISPLAY_NAME,
//    MediaStore.Audio.Media.DATA,
//    MediaStore.Audio.Genres.CONTENT_TYPE

    String album;
    String artist;
    String title;
    int isMusic;
    String displayName;
    String data;            // Song의 Uri 와 함깨 Contents Resolver 에서 호출해야한다고 써있다.
    String contentType;
    Uri contentUri;


    public AudioColumnClass(Uri contentUri) {
        this.contentUri = contentUri;
    }

    protected AudioColumnClass(Parcel in) {
        album = in.readString();
        artist = in.readString();
        title = in.readString();
        isMusic = in.readInt();
        displayName = in.readString();
        data = in.readString();
        contentType = in.readString();
        contentUri = in.readParcelable(Uri.class.getClassLoader());
    }

    public static final Creator<AudioColumnClass> CREATOR = new Creator<AudioColumnClass>() {
        @Override
        public AudioColumnClass createFromParcel(Parcel in) {
            return new AudioColumnClass(in);
        }

        @Override
        public AudioColumnClass[] newArray(int size) {
            return new AudioColumnClass[size];
        }
    };

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getIsMusic() {
        return isMusic;
    }

    public void setIsMusic(int isMusic) {
        this.isMusic = isMusic;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public Uri getContentUri() {
        return contentUri;
    }

    @Override
    public String toString() {
        return "AudioColumnClass{" +
                "album='" + album + '\'' +
                ", artist='" + artist + '\'' +
                ", title='" + title + '\'' +
                ", isMusic=" + isMusic +
                ", displayName='" + displayName + '\'' +
                ", data='" + data + '\'' +
                ", contentType='" + contentType + '\'' +
                ", contentUri=" + contentUri.toString() +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(album);
        dest.writeString(artist);
        dest.writeString(title);
        dest.writeInt(isMusic);
        dest.writeString(displayName);
        dest.writeString(data);
        dest.writeString(contentType);
        dest.writeParcelable(contentUri, flags);
    }
}
