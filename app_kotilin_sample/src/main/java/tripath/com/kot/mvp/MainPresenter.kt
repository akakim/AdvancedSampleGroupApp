package tripath.com.kot.mvp

import android.content.Context

/**
 * Created by RyoRyeong Kim on 2017-12-28.
 * reference : https://github.com/taehwandev/AndroidMVPSample
 */


class MainPresenter : MainContract.Presenter{

    lateinit override var view: MainContract.View
    lateinit override var imageData: TaeWonImageRepository

    lateinit override var adapterModel : ImageAdapterContract.Model

    override var adapterView: ImageAdapterContract.View?  = null
        set(value) {
            field = value
            field?.onClickFunc ={ onClickListener(it)}
        }

    override fun loadItem(context: Context, isClear: Boolean) {

        imageData.getImages(context,10,object : TaeWonImageSource.LoadImageCallback {
            override fun onLoadImage(list: ArrayList<ImageItem>) {

                if(isClear){
                    adapterModel.clearItem()
                }

                adapterModel.setItem( list)
                adapterView?.notifyAdapter()
            }
        })
    }

    private fun onClickListener(position: Int){
        adapterModel.getItem(position).let {
            view.showToast(position)
        }
    }
}