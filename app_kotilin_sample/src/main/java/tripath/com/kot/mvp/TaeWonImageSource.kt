package tripath.com.kot.mvp

import android.content.Context

/**
 * Created by RyoRyeong Kim on 2017-12-28.
 *  reference : https://github.com/taehwandev/AndroidMVPSample
 */

interface TaeWonImageSource {

    interface LoadImageCallback{

        fun onLoadImage(list: ArrayList<ImageItem> )
    }

    fun getImages(context: Context, size: Int,loadImageCallback: LoadImageCallback?)
}