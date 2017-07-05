package rqg.fantasy.open163.tv.business.main

import rqg.fantasy.open163.tv.BasePresenter
import rqg.fantasy.open163.tv.BaseView
import rqg.fantasy.open163.tv.model.MovieItem

/**
 * Created by rqg on 02/07/2017.
 */


interface MainContract {
    interface Presenter : BasePresenter {
        fun loadTypeContent(key: String)

        fun search(word: String)


    }


    interface View : BaseView {
        fun showMovieList(movieList: List<MovieItem>)

        fun showMenuList(cnameList: List<String>)
    }
}