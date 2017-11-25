package group.sample.advanced.rrk.com.advancedsamplegroupapplication.common;

import android.os.Bundle;
import android.support.v4.app.Fragment;

/**
 * @author KIM
 * @version 0.0.1
 * @date 2017-11-25
 * @since 0.0.1
 */

public class BaseFragment extends Fragment {
    public static final String FRAGMENT_NAME= "FragmentName";
    protected String fragmentName;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            fragmentName = getArguments().getString(FRAGMENT_NAME);
        }else {
            fragmentName = "";
        }
    }


    public String getFragmentName() {
        return fragmentName;
    }

    public void setFragmentName(String fragmentName) {
        this.fragmentName = fragmentName;
    }
}
