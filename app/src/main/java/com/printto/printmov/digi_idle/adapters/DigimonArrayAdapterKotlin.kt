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
import com.printto.printmov.digi_idle.utils.SaveManager
import java.util.*

class DigimonArrayAdapterKotlin(var context: Context, var rowItems: ArrayList<Digimon>) : BaseAdapter() {

    var saveManager: SaveManager = SaveManager()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View? {
        var holder: ViewHolder? = null
        var convertView = convertView
        val mInflater = context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        convertView = mInflater.inflate(R.layout.digivolve_item, parent, false)
        holder = ViewHolder()
        holder.digimon_image = convertView.findViewById(R.id.digimon_image) as ImageView
        holder.digimon_name = convertView.findViewById(R.id.digimon_name) as TextView
        holder.availability = convertView.findViewById(R.id.availability) as TextView
        holder.d_image = convertView.findViewById(R.id.d_image) as ImageView
        val row_pos = rowItems.get(position)
        val drawable = AppCompatResources.getDrawable(context, row_pos.profilePic )
        holder.digimon_image!!.setImageDrawable(drawable)
        holder.digimon_name!!.setText(row_pos.name)
        holder.availability!!.setText(DigimonFactory.getRequirementString(row_pos.getName()));
        saveManager.loadState()
        if(DigimonFactory.checkDigivolve(saveManager.getPlayer(), saveManager.getDigimon(), row_pos.getName())){
            holder.d_image!!.setImageResource(R.drawable.ggold)
        }
        else{
            holder.d_image!!.setImageResource(R.drawable.gred)
        }
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
        internal var digimon_image: ImageView? = null
        internal var digimon_name: TextView? = null
        internal var availability: TextView? = null
        internal var d_image: ImageView? = null
    }

    private inner class Drawer {
        lateinit var drawable: Drawable
    }

}