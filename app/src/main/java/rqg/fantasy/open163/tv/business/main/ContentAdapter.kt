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


class ContentAdapter : RecyclerView.Adapter<ContentAdapter.ContentHodler>() {
    val TAG = "ContentAdapter"

    var mMovieList: List<MovieItem> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ContentHodler {
        val view = LayoutInflater.from(parent?.context).inflate(R.layout.item_movie, parent, false)
        return ContentHodler(view)
    }

    override fun onBindViewHolder(holder: ContentHodler?, position: Int) {
        val movie = mMovieList[position]

        holder?.movieCover?.setImageURI(movie.imgpath)
        holder?.movieTitle?.text = movie.title
    }

    override fun getItemCount(): Int {
        return mMovieList.size
    }


    fun setMovieList(mList: List<MovieItem>) {
        Log.d(TAG, "setMovieList() called with: mList = [ ${mList.size} ]")
        mMovieList = mList
        notifyDataSetChanged()
    }


    class ContentHodler(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val movieCover = itemView.movie_cover
        val movieTitle = itemView.movie_title
    }
}