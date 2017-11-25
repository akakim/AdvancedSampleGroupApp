package group.sample.advanced.rrk.com.advancedsamplegroupapplication.samples.database;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import group.sample.advanced.rrk.com.advancedsamplegroupapplication.BaseActivity;
import group.sample.advanced.rrk.com.advancedsamplegroupapplication.MusicFragment;
import group.sample.advanced.rrk.com.advancedsamplegroupapplication.R;
import group.sample.advanced.rrk.com.advancedsamplegroupapplication.common.CommonViewPagerAdapter;
import group.sample.advanced.rrk.com.advancedsamplegroupapplication.data.AudioColumnClass;

/**
 * @author KIM
 * @version 0.0.1
 * @date 2017-11-25
 * @since 0.0.1
 */

public class GetAllOfMusicItemsActivity extends BaseActivity implements MusicFragment.OnFragmentInteractionListener{

    @BindView(R.id.viewPager)
    ViewPager viewPager;

    @BindView(R.id.tabLayout)
    TabLayout tabLayout;

    CommonViewPagerAdapter adapter;


    private List<MusicFragment> musicFragmentList = new ArrayList<>();

    ArrayList<AudioColumnClass> audioColumnClasses = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_all_of_items);

        ButterKnife.bind( this );

        initContentsResolvers();
        adapter =  new CommonViewPagerAdapter(getSupportFragmentManager(),musicFragmentList);
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        tabLayout.setupWithViewPager(viewPager);
        viewPager.setAdapter(adapter);
    }

    private void initContentsResolvers () {
        ContentResolver contentResolver = getContentResolver();


        Uri songUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;


        String [] songProjection ={
                MediaStore.Audio.Media.ALBUM,
                MediaStore.Audio.Media.ARTIST,
                MediaStore.Audio.Media.TITLE,
                MediaStore.Audio.Media.IS_MUSIC,
                MediaStore.Audio.Media.DISPLAY_NAME,
                MediaStore.Audio.Media.DATA,

        };

        Cursor songCursor = contentResolver.query(songUri,songProjection,null,null,null);

        while(songCursor.moveToNext()){


            AudioColumnClass item = new AudioColumnClass(songUri);
            item.setAlbum( songCursor.getString(0) );
            item.setArtist( songCursor.getString(1) );
            item.setTitle( songCursor.getString(2) );
            item.setIsMusic( songCursor.getInt(3) );
            item.setDisplayName( songCursor.getString(4) );
            item.setData( songCursor.getString(5) );
            item.setContentType( "" );
            Log.d(getClass().getSimpleName(),"item" + item.toString() );
            audioColumnClasses.add(item);

//            contentResolver.openFileDescriptor(songUri,)
        }


        for (int k = 0; k< 3; k++){
            MusicFragment musicFragment = new MusicFragment();
            Bundle bundle = new Bundle();
            bundle.putString(MusicFragment.FRAGMENT_NAME,"tab " + k );
            bundle.putParcelableArrayList(MusicFragment.ITEMS_KEY,audioColumnClasses);
            musicFragment.setArguments( bundle );
            musicFragmentList.add( musicFragment);
        }
    }

    @Override
    public void onFragmentInteraction(Uri uri,String tag) {

    }
}
