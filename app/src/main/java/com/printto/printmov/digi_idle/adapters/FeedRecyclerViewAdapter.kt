package com.printto.printmov.digi_idle.adapters

import android.content.Context
import android.graphics.drawable.AnimationDrawable
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.printto.printmov.digi_idle.R
import com.printto.printmov.digi_idle.item.Item
import kotlinx.android.synthetic.main.feed_item.view.*

import java.util.ArrayList

class FeedRecyclerViewAdapter(private val mContext: Context, private val itemsMap: Map<Item, Int>, private val clickListener: ClickListener) : RecyclerView.Adapter<FeedRecyclerViewAdapter.ViewHolder>() {

    interface ClickListener {
        fun onItemClick(position: Int, v: View)
    }

    private var mItems : ArrayList<Item>

    init {
        var temp = ArrayList<Item>()
        temp.addAll(itemsMap.keys)
        mItems  = ArrayList<Item>( temp.sortedWith(compareBy({ it.id })).toList() )
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View) {
            clickListener.onItemClick(getAdapterPosition(), v)
        }

        fun bind(item: Item) = with(itemView) {
            item_name.text = item.getName()
            item_picture.setImageResource(item.getPicture())
            var count = -1
            itemsMap.get(item)?.let{ count = it }
            if( count >= 1){
                itemCount.text = itemsMap.get(item).toString()
            }
            else {
                itemCount.text = ""
            }
            val overlayAnimation = itemOverlay!!.drawable as AnimationDrawable
            overlayAnimation.start()
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeedRecyclerViewAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.feed_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: FeedRecyclerViewAdapter.ViewHolder, position: Int) {
        holder.bind(mItems.get(position))
    }

    override fun getItemCount(): Int {
        return mItems.size
    }

    fun getItemFromPosition(position: Int) : Item{
        return mItems.get(position);
    }

}