package rqg.fantasy.open163.tv.business.main

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.item_movie.view.*
import rqg.fantasy.open163.tv.R
import rqg.fantasy.open163.tv.model.MovieItem

/**
 * Created by rqg on 02/07/2017.
 */


class ContentAdapter : RecyclerView.Adapter<ContentAdapter.ContentHolder>() {
    val TAG = "ContentAdapter"

    var mMovieList: List<MovieItem> = mutableListOf()

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

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ContentHolder {
        val view = LayoutInflater.from(parent?.context).inflate(R.layout.item_movie, parent, false)
        return ContentHolder(view)
    }

    override fun onBindViewHolder(holder: ContentHolder?, position: Int) {
        val movie = mMovieList[position]

        holder?.movieCover?.setImageURI(movie.imgpath)
        holder?.movieTitle?.text = movie.title

        holder?.itemView?.let {
            if (position == selected && showHighLight) {
                it.setBackgroundResource(R.drawable.round_stroke_white_bg)
            } else {
                it.setBackgroundResource(R.drawable.transparent_bg)
            }
        }
    }

    override fun getItemCount(): Int {
        return mMovieList.size
    }


    fun setMovieList(mList: List<MovieItem>) {
        Log.d(TAG, "setMovieList() called with: mList = [ ${mList.size} ]")
        mMovieList = mList
        notifyDataSetChanged()
    }


    class ContentHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val movieCover = itemView.movie_cover
        val movieTitle = itemView.movie_title
    }
}