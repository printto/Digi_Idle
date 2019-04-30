package com.printto.printmov.digi_idle.adapters

import android.content.Context
import android.graphics.drawable.AnimationDrawable
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.printto.printmov.digi_idle.R
import com.printto.printmov.digi_idle.digimon.Digimon
import com.printto.printmov.digi_idle.item.Item
import kotlinx.android.synthetic.main.feed_item.view.*
import kotlinx.android.synthetic.main.item_dex.view.*

import java.util.ArrayList

class DexRecyclerViewAdapter(private val mContext: Context, private val itemsMap: List<Digimon>) : RecyclerView.Adapter<DexRecyclerViewAdapter.ViewHolder>() {

    private var mItems : List<Digimon>

    init {
//        var temp = itemsMap
//        mItems  = ArrayList<Digimon>( temp.sortedWith(compareBy({ it.name })).toList() )
        mItems = itemsMap
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(digimon: Digimon) = with(itemView) {
            digimon_name.text = digimon.getName()
            digimon_picture.setImageResource(digimon.profilePic)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DexRecyclerViewAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_dex, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: DexRecyclerViewAdapter.ViewHolder, position: Int) {
        holder.bind(mItems.get(position))
    }

    override fun getItemCount(): Int {
        return mItems.size
    }

    fun getItemFromPosition(position: Int) : Digimon{
        return mItems.get(position);
    }

}