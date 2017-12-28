package tripath.com.kot.mvp

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import tripath.com.kot.R

/**
 * Created by RyoRyeong Kim on 2017-12-28.
 *
 * 세상에 맙소사 이것 만으로도 초기화가 끝이다.
 */

class ImageViewHolder(val context: Context, parent: ViewGroup?,val listenerFunc : ((Int) ->Unit)? )
    :RecyclerView.ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_image,parent,false)){

    val ivDefault by lazy {
        itemView.findViewById(R.id.ivDefault) as ImageView
    }

    val tvContent by lazy {
        itemView.findViewById(R.id.tvContent) as TextView
    }

    fun onBind(item: ImageItem, position : Int){
        ImageAsynchronize(context,ivDefault).execute(item.resources)

//        Glide.with(context).load( item.resources).into(ivDefault).onLoadFailed();

        tvContent.text = item.title

        itemView.setOnClickListener {
            listenerFunc?.invoke(position)
        }
    }

}