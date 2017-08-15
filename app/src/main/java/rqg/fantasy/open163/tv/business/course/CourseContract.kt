package rqg.fantasy.open163.tv.business.course

import rqg.fantasy.open163.tv.BasePresenter
import rqg.fantasy.open163.tv.BaseView
import rqg.fantasy.open163.tv.model.MovieItem

/**
 * * Created by rqg on 14/08/2017.
 */


interface CourseContract {

    interface Presenter : BasePresenter {
        fun loadCourseDetail()
    }

    interface View : BaseView {
        var courseData: MovieItem

        fun updateCourseInfoUi()


    }
}