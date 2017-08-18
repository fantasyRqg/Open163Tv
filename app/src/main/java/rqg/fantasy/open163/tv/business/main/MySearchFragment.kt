package rqg.fantasy.open163.tv.business.main

import android.os.Bundle
import android.os.Handler
import android.support.v17.leanback.app.SearchFragment
import android.support.v17.leanback.widget.ArrayObjectAdapter
import android.support.v17.leanback.widget.ListRowPresenter
import android.support.v17.leanback.widget.ObjectAdapter
import android.text.TextUtils

class MySearchFragment : SearchFragment(), SearchFragment.SearchResultProvider {
    companion object {

        private val SEARCH_DELAY_MS = 300L
    }

    private lateinit var mRowsAdapter: ArrayObjectAdapter
    private val mHandler = Handler()
    private var mDelayedLoad = Runnable {

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mRowsAdapter = ArrayObjectAdapter(ListRowPresenter())
        setSearchResultProvider(this)
    }

    override fun getResultsAdapter(): ObjectAdapter {
        return mRowsAdapter
    }

    override fun onQueryTextChange(newQuery: String): Boolean {
        mRowsAdapter.clear()
        if (!TextUtils.isEmpty(newQuery)) {
//            mDelayedLoad.setSearchQuery(newQuery)
            mHandler.removeCallbacks(mDelayedLoad)
            mHandler.postDelayed(mDelayedLoad, SEARCH_DELAY_MS)
        }
        return true
    }

    override fun onQueryTextSubmit(query: String): Boolean {
        mRowsAdapter.clear()
        if (!TextUtils.isEmpty(query)) {
//            mDelayedLoad.setSearchQuery(query)
            mHandler.removeCallbacks(mDelayedLoad)
            mHandler.postDelayed(mDelayedLoad, SEARCH_DELAY_MS)
        }
        return true
    }


}