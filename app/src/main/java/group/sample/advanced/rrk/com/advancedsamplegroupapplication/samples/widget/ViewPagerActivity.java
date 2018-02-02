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
import group.sample.advanced.rrk.com.advancedsamplegroupapplication.samples.TestFragment;
import group.sample.advanced.rrk.com.advancedsamplegroupapplication.views.SimpleListFragment;

public class ViewPagerActivity extends AppCompatActivity  implements
        SimpleListFragment.OnFragmentInteractionListener,
        TestFragment.OnFragmentInteractionListener{

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

        ArrayList<Fragment> defaultFragments = new ArrayList<>();
        ArrayList<Fragment> fragments = new ArrayList<>();
        public RecyclerViewFragmentAdapter(FragmentManager fm , ArrayList<Fragment> fragments) {
            super(fm);
            this.fragments = fragments ;




            for (int position = 0; position<=2;position++) {

                TestFragment instance = new TestFragment();
                defaultFragments.add( instance );

            }
        }


        @Override
        public Object instantiateItem(ViewGroup container, int position) {

            Object obj = super.instantiateItem(container, position);
            if( obj instanceof Fragment ){

                if( position < 3 ){


                    Bundle bundle = new Bundle();

                    final String param1 = "param1";
                    final String param2 = "param2";
                    switch (position) {
                        case 0:
                            bundle.putString(param1, "첫번째 서브내용");
                            bundle.putString(param2, "청하는 마싰다 ");
                            break;
                        case 1:
                            bundle.putString(param1, "두번째 서브내용");
                            bundle.putString(param2, "청하는 머글꺼 ");
                            break;
                        case 2:
                            bundle.putString(param1, "세번째 서브내용 ");
                            bundle.putString(param2, "우걱우걱 ");
                            break;
                        default:
                            break;

                    }


                    ((Fragment) obj).setArguments(bundle);
                }
                else {
                    ArrayList<String> strList = new ArrayList<>();
                    for (int j = position * 10; j < (position * 10) + 30; j++) {
                        strList.add("inserted String " + j);
                    }


                    Bundle bundle = new Bundle();
                    bundle.putStringArrayList(SimpleListFragment.ARG_LIST_ITEM, strList);


                    ((Fragment) obj).setArguments(bundle);
                }
            }
            return  obj ;
        }

        @Override
        public Fragment getItem(int position) {

            if (position < 3) {

                return defaultFragments.get(position);
            } else if(position>=3){
                return fragments.get(position - 3);
            }else {
                return null;
            }
        }

        @Override
        public int getCount() {
            return defaultFragments.size()+ fragments.size();
        }
    }
}
