package rqg.fantasy.open163.tv.business.main

import android.util.Log
import rqg.fantasy.open163.tv.ActivityScope
import rqg.fantasy.open163.tv.model.MovieItem
import javax.inject.Inject

/**
 * Created by rqg on 02/07/2017.
 */


@ActivityScope
class MainPresenterImpl @Inject constructor(view: MainContract.View) : MainContract.Presenter {

    private data class PlayListItem(val mCname: String, val mMovieList: List<MovieItem>)

    val TAG = "MainPresenterImpl"

    private val mPlayTable: List<PlayListItem> = mutableListOf()

    override fun start() {
        Log.d(TAG, "start() called")
    }

    override fun loadTypeContent(index: Int): List<MovieItem> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun search(word: String): List<MovieItem> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}