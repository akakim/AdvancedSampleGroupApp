package tripath.com.kot.mvp

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.ViewGroup

/**
 * Created by RyoRyeong Kim on 2017-12-28.
 */

class ImageAdapter(val context:Context) : ImageAdapterContract.View, RecyclerView.Adapter<ImageViewHolder>(), ImageAdapterContract.Model{

    private lateinit var imageList : ArrayList<ImageItem>


    override fun setItem(imageItems: ArrayList<ImageItem>) {
        this.imageList = imageItems

        for( item in imageItems ){
            Log.d("setItem()", item.title )
            Log.d("setItem()", item.resources.toString() )
        }
    }

    override fun addItems(imageItem: ImageItem) {
        imageList?.add( imageItem )
    }

    override fun clearItem() {

        imageList?.clear()
    }

    override fun getItem(position: Int): ImageItem {
        return imageList[position]
    }

    override fun onBindViewHolder(holder: ImageViewHolder?, position: Int) {

        imageList[position].let{
            holder?.onBind( it , position )
        }
    }

    override fun getItemCount() = imageList.size

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int) =  ImageViewHolder ( context,parent,onClickFunc)

    override var onClickFunc: ((Int) -> Unit)? = null

    override fun notifyAdapter() {
        notifyDataSetChanged()
        Log.d(this.javaClass.simpleName,"notifyAdapter()")
    }
}