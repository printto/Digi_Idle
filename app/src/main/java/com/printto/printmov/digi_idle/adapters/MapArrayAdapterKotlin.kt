package com.printto.printmov.digi_idle.adapters

import android.app.Activity
import android.content.Context
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
import com.printto.printmov.digi_idle.factory.DigimonFactory
import com.printto.printmov.digi_idle.farmmap.FarmMap
import com.printto.printmov.digi_idle.utils.SaveManager
import java.util.*

class MapArrayAdapterKotlin(var context: Context, var rowItems: ArrayList<FarmMap>) : BaseAdapter() {

//    var saveManager: SaveManager = SaveManager()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View? {
        var holder: ViewHolder? = null
        var convertView = convertView
        val mInflater = context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        convertView = mInflater.inflate(R.layout.item_farmmap, parent, false)
        holder = ViewHolder()
        holder.map_image = convertView.findViewById(R.id.map_image) as ImageView
        holder.map_name = convertView.findViewById(R.id.map_name) as TextView
        holder.map_desc = convertView.findViewById(R.id.desc_text) as TextView
        val row_pos = rowItems.get(position)
        holder.map_image!!.setImageResource(row_pos.getBackgroundResource())
        holder.map_name!!.setText(row_pos.getMapName())
        holder.map_desc!!.setText(row_pos.getDescription())
        convertView.tag = holder
        return convertView
    }

    override fun getItem(position: Int): Any {
        return rowItems.get(position)
    }

    override fun getItemId(position: Int): Long {
        return rowItems.indexOf(getItem(position)).toLong();
    }

    override fun getCount(): Int {
        return rowItems.size
    }

    private inner class ViewHolder {
        internal var map_image: ImageView? = null
        internal var map_name: TextView? = null
        internal var map_desc: TextView? = null
    }

    private inner class Drawer {
        lateinit var drawable: Drawable
    }

}