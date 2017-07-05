package rqg.fantasy.open163.tv.business.main

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.item_play_menu.view.*
import rqg.fantasy.open163.tv.R

/**
 * Created by rqg on 02/07/2017.
 */


class MenuAdapter(inline val menuClick: (String) -> Unit) : RecyclerView.Adapter<MenuAdapter.MenuHolder>() {
    var mCnameList: List<String> = listOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): MenuHolder {
        val view = LayoutInflater.from(parent?.context).inflate(R.layout.item_play_menu, parent, false)

        return MenuHolder(view)
    }

    override fun onBindViewHolder(holder: MenuHolder?, position: Int) {
        holder?.cnameView?.text = mCnameList[position]
        holder?.cnameView?.setOnClickListener {
            holder.cnameView?.text?.let {
                menuClick.invoke(it.toString())
            }
        }
    }

    override fun getItemCount(): Int {
        return mCnameList.size
    }


    class MenuHolder(view: View) : RecyclerView.ViewHolder(view) {
        val cnameView = view.cname
    }
}