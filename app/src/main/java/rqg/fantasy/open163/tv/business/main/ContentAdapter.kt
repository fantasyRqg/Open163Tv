package rqg.fantasy.open163.tv.business.main

import android.support.v7.widget.RecyclerView
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
    var mMovieList: List<MovieItem> = listOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ContentHodler {
        var view = LayoutInflater.from(parent?.context).inflate(R.layout.item_movie, parent, false)
        return ContentHodler(view)
    }

    override fun onBindViewHolder(holder: ContentHodler?, position: Int) {
        var movie = mMovieList[position]

        holder?.movieCover?.setImageURI(movie.imgpath)
        holder?.movieTitle?.text = movie.title
    }

    override fun getItemCount(): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


    class ContentHodler(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val movieCover = itemView.movie_cover
        val movieTitle = itemView.movie_title
    }
}