package rqg.fantasy.open163.tv.business.main

import android.annotation.SuppressLint
import android.app.LoaderManager
import android.content.AsyncTaskLoader
import android.content.Context
import android.content.Loader
import android.os.Bundle
import android.support.v17.leanback.app.BrowseFragment
import rqg.fantasy.open163.tv.model.MovieItem

/**
 * *Created by rqg on 8/18/17 9:36 AM.
 */
typealias CourseDataSet = Map<String, MutableList<MovieItem>>

class MainFragment : BrowseFragment(), LoaderManager.LoaderCallbacks<CourseDataSet> {

    override fun onCreateLoader(id: Int, args: Bundle?): Loader<CourseDataSet> {
        return DataLoader(activity)
    }

    override fun onLoadFinished(loader: Loader<CourseDataSet>?, data: CourseDataSet?) {


    }

    override fun onLoaderReset(loader: Loader<CourseDataSet>?) {


    }

    @SuppressLint("StaticFieldLeak")
    class DataLoader(context: Context) : AsyncTaskLoader<CourseDataSet>(context) {
        override fun loadInBackground(): CourseDataSet {
            return (context as MainActivity).getCourseData()
        }
    }

}

