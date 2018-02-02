package group.sample.advanced.rrk.com.advancedsamplegroupapplication.samples.widget;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import group.sample.advanced.rrk.com.advancedsamplegroupapplication.R;
import group.sample.advanced.rrk.com.advancedsamplegroupapplication.widget.FirstFragment;
import group.sample.advanced.rrk.com.advancedsamplegroupapplication.widget.SecondFragment;
import group.sample.advanced.rrk.com.advancedsamplegroupapplication.widget.ThirdFragment;

/**
 *
 */
public class CustomViewPagerActivity extends AppCompatActivity {


    @BindView(R.id.nestedScrollView)
    NestedScrollView nestedScrollView;
    @BindView(R.id.viewPager)
    ViewPager viewPager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_view_pager);

        ButterKnife.bind(this);
    }




    private class CustomViewPagerAdapter extends FragmentStatePagerAdapter{


        private List<Fragment> fragmentList = new ArrayList<>();
        public CustomViewPagerAdapter(FragmentManager fm) {
            super(fm);
            fragmentList.add(new FirstFragment());
            fragmentList.add(new SecondFragment());
            fragmentList.add(new ThirdFragment());

        }


        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            return super.instantiateItem(container, position);
        }

        @Override
        public Fragment getItem(int position) {
            return fragmentList.get(position);
        }

        @Override
        public int getCount() {
            return fragmentList.size();
        }
    }



}
