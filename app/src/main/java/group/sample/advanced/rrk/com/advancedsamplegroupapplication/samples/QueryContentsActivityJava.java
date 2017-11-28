package group.sample.advanced.rrk.com.advancedsamplegroupapplication.samples;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import group.sample.advanced.rrk.com.advancedsamplegroupapplication.BaseActivity;
import group.sample.advanced.rrk.com.advancedsamplegroupapplication.R;

/**
 * Created by RyoRyeong Kim on 2017-11-23.
 */

public class QueryContentsActivityJava extends BaseActivity {

    @BindView(R.id.vpDeviceContents)
    ViewPager vpDeviceContents;

    ViewPagerAdapter viewPagerAdapter;

    List<> fragments
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ButterKnife.bind( this );


        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(),)
        vpDeviceContents.setAdapter( viewPagerAdapter );
    }


    private class ViewPagerAdapter extends FragmentPagerAdapter{
        List<Fragment> fragments;
        public ViewPagerAdapter(FragmentManager fm, List<Fragment> fragments) {
            super(fm);

            this.fragments = new ArrayList<>();
            this.fragments.addAll(fragments);
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }
    }
}
