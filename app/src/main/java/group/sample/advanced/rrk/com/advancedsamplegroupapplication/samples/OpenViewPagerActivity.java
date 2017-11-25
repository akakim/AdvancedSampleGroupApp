package group.sample.advanced.rrk.com.advancedsamplegroupapplication.samples;

import android.net.Uri;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import group.sample.advanced.rrk.com.advancedsamplegroupapplication.ListFragment;
import group.sample.advanced.rrk.com.advancedsamplegroupapplication.R;

@RequiresApi(api = Build.VERSION_CODES.M)
public class OpenViewPagerActivity extends AppCompatActivity implements ListFragment.OnFragmentInteractionListener,
ViewPager.OnScrollChangeListener{



    @BindView(R.id.tabLayout)
    TabLayout tabLayout;

    @BindView(R.id.viewPager)
    ViewPager viewPager;

    List<Fragment> fragments;

    SimplePagerAdapter simplePagerAdapter ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_view_pager);

        ButterKnife.bind( this );


        fragments = new ArrayList<>();


        for( int k = 0; k< 5;k++){

            Fragment fragment = new ListFragment();

            Bundle bundle = new Bundle();
            bundle.putString(ListFragment.ITEM_KEY,"insert arguments " + k);
            fragment.setArguments(bundle);

            fragments.add( fragment );


        }


        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setTabGravity( TabLayout.GRAVITY_FILL);

        simplePagerAdapter = new SimplePagerAdapter( getSupportFragmentManager(), fragments );


        viewPager.setAdapter( simplePagerAdapter );

    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {

    }


    private class SimplePagerAdapter extends FragmentStatePagerAdapter{

        List<Fragment> fragments;

        public SimplePagerAdapter(FragmentManager fm,List<Fragment> fragments) {
            super(fm);

            this.fragments = new ArrayList<>();
            this.fragments.addAll( fragments );
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return "Tab " + position;
        }

        @Override
        public void startUpdate(ViewGroup container) {
            super.startUpdate(container);
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            super.destroyItem(container, position, object);
        }

        @Override
        public void finishUpdate(ViewGroup container) {
            super.finishUpdate(container);
        }
    }
}
