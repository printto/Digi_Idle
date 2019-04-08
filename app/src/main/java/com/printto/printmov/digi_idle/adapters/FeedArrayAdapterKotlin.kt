package com.printto.printmov.digi_idle.adapters

import android.app.Activity
import android.content.Context
import android.graphics.drawable.AnimationDrawable
import android.graphics.drawable.Drawable
import android.support.v7.content.res.AppCompatResources
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.printto.printmov.digi_idle.R
import com.printto.printmov.digi_idle.digimon.Digimon
import com.printto.printmov.digi_idle.digimon.DigimonFactory
import com.printto.printmov.digi_idle.item.Item
import com.printto.printmov.digi_idle.utils.SaveManager
import java.util.*



class FeedArrayAdapterKotlin(var context: Context, var rowItems: Map<Item, Integer>) : BaseAdapter() {

    var keys = ArrayList(rowItems.keys);

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View? {
        var holder: ViewHolder? = null
        var convertView = convertView
        val mInflater = context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        convertView = mInflater.inflate(R.layout.feed_item, parent, false)
        holder = ViewHolder()
        holder.item_picture = convertView.findViewById(R.id.digimon_image) as ImageView
        holder.item_name = convertView.findViewById(R.id.digimon_name) as TextView
        val row_pos = keys.get(position)
        val drawable = AppCompatResources.getDrawable(context, row_pos.picture )
        holder.item_picture!!.setImageDrawable(drawable)
        holder.item_name!!.setText(row_pos.name)
        holder.itemCount!!.setText(rowItems.get(row_pos).toString())
        convertView.tag = holder
        val overlay : ImageView = convertView.findViewById(R.id.itemOverlay)
        val animationDrawable : AnimationDrawable = overlay.drawable as AnimationDrawable;
        animationDrawable.start();
        return convertView
    }

    override fun getItem(position: Int): Any {
        return keys.get(position)
    }

    override fun getItemId(position: Int): Long {
        return keys.indexOf(getItem(position)).toLong();
    }

    override fun getCount(): Int {
        return rowItems.size
    }

    private inner class ViewHolder {
        internal var item_picture: ImageView? = null
        internal var item_name: TextView? = null
        internal var itemCount: TextView? = null
    }

    private inner class Drawer {
        lateinit var drawable: Drawable
    }

}