package group.sample.advanced.rrk.com.advancedsamplegroupapplication.samples.database;

import android.content.ContentProvider;
import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import butterknife.BindView;
import butterknife.ButterKnife;
import group.sample.advanced.rrk.com.advancedsamplegroupapplication.BaseActivity;
import group.sample.advanced.rrk.com.advancedsamplegroupapplication.R;
import group.sample.advanced.rrk.com.advancedsamplegroupapplication.common.CommonViewPagerAdapter;

public class GetAllOfItems extends BaseActivity {



    @BindView(R.id.viewPager)
    ViewPager viewPager;

    CommonViewPagerAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_all_of_items);

        ButterKnife.bind( this );



    }


    private void initContentsResolvers (){
        ContentResolver contentResolver = getContentResolver();

        Uri songUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        int mediaTypeNone = MediaStore.Files.FileColumns.MEDIA_TYPE_NONE;
        Uri thumbnailUri = MediaStore.Images.Thumbnails.EXTERNAL_CONTENT_URI;
        Uri imageUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
        Uri fileUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
//        Uri fileUri = MediaStore.Files

        String [] songProjection ={
                MediaStore.Audio.Media.ALBUM,
                MediaStore.Audio.Media.ARTIST,
                MediaStore.Audio.Media.TITLE,
                MediaStore.Audio.Media.DISPLAY_NAME,
                MediaStore.Audio.Media.DATA
        };

        Cursor songCursor =        contentResolver.query(songUri,songProjection,null,null,null);
    }

}
