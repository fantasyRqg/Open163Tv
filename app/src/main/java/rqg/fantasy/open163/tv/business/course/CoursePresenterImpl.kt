package rqg.fantasy.open163.tv.business.course

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import rqg.fantasy.open163.tv.api.Open163Api
import javax.inject.Inject

/**
 * * Created by rqg on 15/08/2017.
 */


class CoursePresenterImpl @Inject constructor(val view: CourseContract.View, val open163Api: Open163Api)
    : CourseContract.Presenter {
    val TAG = "CoursePresenterImpl"

    override fun loadCourseDetail() {
        view.courseData.plid?.let {
            open163Api.getMovies(it)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe {
                        view.courseData = it
                        view.updateCourseInfoUi()
                    }
        }
    }


    override fun start() {

    }

}