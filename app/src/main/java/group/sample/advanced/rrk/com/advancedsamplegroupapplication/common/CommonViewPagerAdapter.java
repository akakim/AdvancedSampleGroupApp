package group.sample.advanced.rrk.com.advancedsamplegroupapplication.common;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author KIM
 * @version 0.0.1
 * @date 2017-11-25
 * @since 0.0.1
 * 기본적인 샘플에 사용할 ViewPager를 위한 어뎁터
 */

public class CommonViewPagerAdapter  extends FragmentStatePagerAdapter{

    List<BaseFragment> fragments;
    public CommonViewPagerAdapter(FragmentManager fm,List<? extends BaseFragment> baseFragments) {
        super(fm);


        fragments = new ArrayList<>();
        fragments.addAll( baseFragments );

    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }


    @Override
    public CharSequence getPageTitle(int position) {
        return fragments.get(position).getFragmentName();
    }

    @Override
    public int getCount() {
        return fragments.size();
    }
}
