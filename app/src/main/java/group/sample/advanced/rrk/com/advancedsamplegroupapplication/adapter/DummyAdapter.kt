package group.sample.advanced.rrk.com.advancedsamplegroupapplication.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import group.sample.advanced.rrk.com.advancedsamplegroupapplication.R

/**
 * @author KIM
 * @version 0.0.1
 * @since 0.0.1
 * @date 2018-07-31
 */

open class DummyAdapter : RecyclerView.Adapter<DummyAdapter.ViewHolder> {

    var context : Context
    var list : List<String>

    constructor(context : Context, list : List<String>){
        this.context    = context
        this.list       = list
    }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {

        holder?.apply {
         dummyContents?.text = list.get( position )
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {

        return ViewHolder( LayoutInflater.from( context ).inflate( R.layout.item_single_item,parent,false) )
    }

    override fun getItemCount(): Int {

        return list.size
    }


    class ViewHolder : RecyclerView.ViewHolder{

        val dummyContents : TextView?
        constructor(itemView: View?):super(itemView){

           dummyContents =  itemView?.findViewById(R.id.idText)
        }
    }
}