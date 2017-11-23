package group.sample.advanced.rrk.com.advancedsamplegroupapplication.adapter.sampleadapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import group.sample.advanced.rrk.com.advancedsamplegroupapplication.R
import group.sample.advanced.rrk.com.advancedsamplegroupapplication.data.ContentsItem

/**
 * Created by RyoRyeong Kim on 2017-11-23.
 */


class ContentsAdapter(context: Context,items : MutableList<ContentsItem>,listener : OnRecyclerItemClickListener) : RecyclerView.Adapter<ContentsAdapter.ViewHolder>()  {



    val context : Context
    val items : MutableList<ContentsItem>
    val itemClickListner :View.OnClickListener
    val  listener : OnRecyclerItemClickListener

    init{
        this.context = context
        this.items = items
        this.listener = listener
        this.itemClickListner = View.OnClickListener {
            view ->
            val item = view.tag as Int
            listener.ItemOnClicked( item )
        }

    }


    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {

        holder?.itemView?.tag = position
        holder?.title?.text= items.get(position).name
        holder?.contents?.text= items.get(position).uri.encodedPath

    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        val v : View? =  LayoutInflater.from( context ).inflate( R.layout.contens_item ,null)
        return ViewHolder( v )
    }


    open class ViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {

        val title : TextView
        val  contents : TextView

        init {
            title = itemView!!.findViewById(R.id.title)
            contents = itemView!!.findViewById(R.id.contents)
        }


    }


    interface OnRecyclerItemClickListener{
        fun ItemOnClicked(position : Int)
    }

}