package rqg.fantasy.open163.tv.business.main

import android.annotation.SuppressLint
import android.app.LoaderManager
import android.content.AsyncTaskLoader
import android.content.Context
import android.content.Loader
import android.os.Bundle
import android.support.v17.leanback.app.BrowseFragment
import android.support.v17.leanback.widget.ArrayObjectAdapter
import android.support.v17.leanback.widget.HeaderItem
import android.support.v17.leanback.widget.ListRowPresenter
import rqg.fantasy.open163.tv.model.MovieItem

/**
 * *Created by rqg on 8/18/17 9:36 AM.
 */
typealias CourseDataSet = Map<String, MutableList<MovieItem>>

class MainFragment : BrowseFragment(), LoaderManager.LoaderCallbacks<CourseDataSet> {

    private var mCategoryRowAdapter: ArrayObjectAdapter = ArrayObjectAdapter(ListRowPresenter())


    override fun onAttach(context: Context?) {
        super.onAttach(context)

        loaderManager.initLoader<CourseDataSet>(0, null, null)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        adapter = mCategoryRowAdapter
    }


    override fun onCreateLoader(id: Int, args: Bundle?): Loader<CourseDataSet> {
        return DataLoader(activity)
    }

    override fun onLoadFinished(loader: Loader<CourseDataSet>?, data: CourseDataSet?) {
        if (data == null)
            return


        mCategoryRowAdapter.clear()

        for (ckey in data.keys) {
            val header = HeaderItem(ckey)
        }
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

