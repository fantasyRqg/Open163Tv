package rqg.fantasy.open163.tv.business.main

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import kotlinx.android.synthetic.main.item_play_menu.view.*
import rqg.fantasy.open163.tv.R

/**
 * Created by rqg on 02/07/2017.
 */


class MenuAdapter(inline val menuClick: (String) -> Unit) : RecyclerView.Adapter<MenuAdapter.MenuHolder>() {
    val TAG = "MenuAdapter"

    var cnameList: List<String> = listOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    var selected: Int = 0
        set(value) {
            if (value < 0 || value >= itemCount)
                return

            if (value != field) {
                field = value
                notifyDataSetChanged()
            }
        }

    var showHighLight = false
        set(value) {
            if (value != field) {
                field = value
                notifyDataSetChanged()
            }
        }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): MenuHolder {
        val view = LayoutInflater.from(parent?.context).inflate(R.layout.item_play_menu, parent, false)

        return MenuHolder(view)
    }

    override fun onBindViewHolder(holder: MenuHolder?, position: Int) {
        holder?.cnameView?.text = cnameList[position]


        if (showHighLight) {
            holder?.cnameView?.let {
                if (position == selected) {
                    it.setBackgroundResource(R.drawable.round_stroke_white_bg)
                    if (it is TextView) {
                        menuClick.invoke(it.text.toString())
                    }
                } else {
                    it.setBackgroundResource(R.drawable.transparent_bg)
                }
            }
        }
    }


    override fun getItemCount(): Int {
        return cnameList.size
    }


    class MenuHolder(view: View) : RecyclerView.ViewHolder(view) {
        val cnameView = view.cname
    }
}