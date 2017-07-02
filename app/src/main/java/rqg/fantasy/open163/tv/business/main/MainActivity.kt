package rqg.fantasy.open163.tv.business.main

import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_main.*
import rqg.fantasy.open163.tv.BaseActivity
import rqg.fantasy.open163.tv.R
import rqg.fantasy.open163.tv.model.MovieItem
import javax.inject.Inject

/**
 * Created by rqg on 02/07/2017.
 */

class MainActivity : BaseActivity(), MainContract.View {
    lateinit var mMenuAdapter: MenuAdapter
    lateinit var mContentAdapter: ContentAdapter
    @Inject lateinit var mPresenter: MainContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()

        mPresenter.start()
    }

    private fun initView() {
        mMenuAdapter = MenuAdapter()
        mContentAdapter = ContentAdapter()


        menu_list.adapter = mMenuAdapter
        menu_list.layoutManager = LinearLayoutManager(this)
        menu_list.setHasFixedSize(true)


        content_list.adapter = mContentAdapter
        content_list.layoutManager = GridLayoutManager(this, 4)
        content_list.setHasFixedSize(true)
    }

    override fun showMovieList(movieList: List<MovieItem>) {
        mContentAdapter.mMovieList = movieList
    }

    override fun showMenuList(cnameList: List<String>) {
        mMenuAdapter.mCnameList = cnameList
    }
}