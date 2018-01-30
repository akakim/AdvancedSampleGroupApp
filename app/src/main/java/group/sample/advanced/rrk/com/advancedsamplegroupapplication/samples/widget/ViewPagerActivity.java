 package group.sample.advanced.rrk.com.advancedsamplegroupapplication.samples.widget;

import android.net.Uri;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ViewGroup;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import group.sample.advanced.rrk.com.advancedsamplegroupapplication.R;
import group.sample.advanced.rrk.com.advancedsamplegroupapplication.views.SimpleListFragment;

public class ViewPagerActivity extends AppCompatActivity implements SimpleListFragment.OnFragmentInteractionListener{

    @BindView(R.id.viewPager)
    ViewPager viewPager;
    @BindView(R.id.nestedScrollView)
    NestedScrollView nestedScrollView;


    ArrayList<Fragment> fragments = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager);

        ButterKnife.bind(this);


        for(int k = 0; k<  5; k++){
            SimpleListFragment fs = new SimpleListFragment();

//            ArrayList<String> strList = new ArrayList<>();
//            for( int j = k *10 ; j< (k*10) + 12 ; j++){
//                strList.add("inserted String " + j);
//            }
//
//
//            Bundle bundle =new Bundle();
//            bundle.putStringArrayList(SimpleListFragment.ARG_LIST_ITEM,strList);
//
//            fs.setArguments( bundle );
            fragments.add(fs);
        }


        RecyclerViewFragmentAdapter adapter = new RecyclerViewFragmentAdapter(getSupportFragmentManager(),fragments);


        viewPager.setAdapter( adapter );

        nestedScrollView.setFillViewport( true );
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }


    private class RecyclerViewFragmentAdapter extends FragmentStatePagerAdapter {
        ArrayList<Fragment> fragments = new ArrayList<>();
        public RecyclerViewFragmentAdapter(FragmentManager fm , ArrayList<Fragment> fragments) {
            super(fm);
            this.fragments = fragments ;
        }


        @Override
        public Object instantiateItem(ViewGroup container, int position) {

            Object obj = super.instantiateItem(container, position);
            if( obj instanceof Fragment ){

                ArrayList<String> strList = new ArrayList<>();
                for( int j = position *10 ; j< (position*10) + 30 ; j++){
                    strList.add("inserted String " + j);
                }


                Bundle bundle =new Bundle();
                bundle.putStringArrayList(SimpleListFragment.ARG_LIST_ITEM,strList);


                ((Fragment) obj).setArguments( bundle );
            }
            return  obj ;
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
