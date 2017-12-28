package tripath.com.kot.mvp

import android.content.Context

/**
 * Created by RyoRyeong Kim on 2017-12-28.
 *  reference : https://github.com/taehwandev/AndroidMVPSample
 */

object TaeWonImageRepository : TaeWonImageSource {


    private val localDataSource = TaeWonImageLocalRepository

    override fun getImages(context: Context, size: Int, loadImageCallback: TaeWonImageSource.LoadImageCallback?) {

        localDataSource.getImages(context,size, object : TaeWonImageSource.LoadImageCallback {
            override fun onLoadImage(list: ArrayList<ImageItem>) {
                loadImageCallback?.onLoadImage(list)
            }
        })


    }

}