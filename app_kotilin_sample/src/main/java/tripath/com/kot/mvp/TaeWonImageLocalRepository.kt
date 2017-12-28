package tripath.com.kot.mvp

import android.content.Context

/**
 * Created by RyoRyeong Kim on 2017-12-28.
 *  reference : https://github.com/taehwandev/AndroidMVPSample
 */

object TaeWonImageLocalRepository : TaeWonImageSource{
    override fun getImages(context: Context, size: Int, loadImageCallback: TaeWonImageSource.LoadImageCallback?) {
        val list = ArrayList<ImageItem>()

        for( index in 0..size){
            val name = String.format("sample_%02d",(Math.random() * 15).toInt())
            val resource = context.resources.getIdentifier(name,"drawable",context.packageName)
            list.add(ImageItem(resource,name))
        }

        loadImageCallback?.onLoadImage(list)

    }

}