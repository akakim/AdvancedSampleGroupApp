package tripath.com.kot.mvp

/**
 * Created by RyoRyeong Kim on 2017-12-28.
 */

interface ImageAdapterContract{

    interface View{

        var onClickFunc : ( (Int) ->Unit)?

        fun notifyAdapter()
    }

    interface Model{

        fun setItem( imageItems: ArrayList<ImageItem> )
        fun addItems( imageItem: ImageItem)

        fun clearItem()

        fun getItem(position: Int): ImageItem
    }
}