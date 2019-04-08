package com.printto.printmov.digi_idle.adapters

import android.content.Context
import android.graphics.drawable.AnimationDrawable
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView

import com.printto.printmov.digi_idle.R
import com.printto.printmov.digi_idle.item.Item
import kotlinx.android.synthetic.main.feed_item.view.*

import java.util.ArrayList

class FeedRecyclerViewAdapter(private val mContext: Context, private val itemsMap: Map<Item, Int>) : RecyclerView.Adapter<FeedRecyclerViewAdapter.ViewHolder>() {

    private val mItems = ArrayList<Item>()

    init {
        mItems.addAll(itemsMap.keys)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

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

}