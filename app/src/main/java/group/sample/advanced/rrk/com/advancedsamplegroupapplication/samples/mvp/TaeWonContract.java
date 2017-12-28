package group.sample.advanced.rrk.com.advancedsamplegroupapplication.samples.mvp;

import android.content.Context;

/**
 * Created by RyoRyeong Kim on 2017-12-28.
 */

public interface TaeWonContract {

    interface View{
        void showToast(final String title);
    }

    interface Presenter {

        void attachView(View view );

        void setImageAdapterModel();

        void setImageAdapterView();

        void detaachView();

        void setSampleImageData();

        void loadItem(Context context,boolean isClean);
    }
}
