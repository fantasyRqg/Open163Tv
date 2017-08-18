package rqg.fantasy.open163.tv.business.main

import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.KeyEvent
import android.view.SoundEffectConstants
import com.wyt.searchbox.SearchFragment
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_main.*
import rqg.fantasy.open163.tv.App
import rqg.fantasy.open163.tv.BaseActivity
import rqg.fantasy.open163.tv.R
import rqg.fantasy.open163.tv.business.course.CourseActivity
import rqg.fantasy.open163.tv.model.MovieItem
import javax.inject.Inject

/**
 * Created by rqg on 02/07/2017.
 */

class MainActivity : BaseActivity(), MainContract.View {
    private companion object {
        const val CONTENT_ROW_SPACE = 4
        const val TAG = "MainActivity"
    }


    lateinit var mMenuAdapter: MenuAdapter
    lateinit var mContentAdapter: ContentAdapter
    @Inject lateinit var mPresenter: MainContract.Presenter
    @Inject lateinit var mApp: App

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
        menu_list.requestFocus()


        content_list.adapter = mContentAdapter
        content_list.layoutManager = GridLayoutManager(this, CONTENT_ROW_SPACE)
        content_list.setHasFixedSize(true)

        mMenuAdapter.showHighLight = true
    }

    override fun showMovieList(movieList: List<MovieItem>) {
        runOnUiThread { mContentAdapter.setMovieList(movieList) }
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        menu_list.playSoundEffect(SoundEffectConstants.CLICK)
        when (keyCode) {
            KeyEvent.KEYCODE_DPAD_DOWN -> {
                if (mMenuAdapter.showHighLight) {
                    mMenuAdapter.selected = mMenuAdapter.selected + 1
                    mPresenter.loadTypeContent(mMenuAdapter.cnameList[mMenuAdapter.selected])
                } else {
                    mContentAdapter.selected += CONTENT_ROW_SPACE
                    content_list.smoothScrollToPosition(mContentAdapter.selected)
                }
                return true
            }

            KeyEvent.KEYCODE_DPAD_UP -> {
                if (mMenuAdapter.showHighLight) {
                    mMenuAdapter.selected = mMenuAdapter.selected - 1
                    if (mMenuAdapter.selected == 0) {
                        showSearchFragment()
                    } else {
                        mPresenter.loadTypeContent(mMenuAdapter.cnameList[mMenuAdapter.selected])
                    }
                } else {
                    mContentAdapter.selected -= CONTENT_ROW_SPACE
                    content_list.smoothScrollToPosition(mContentAdapter.selected)
                }

                return true
            }

            KeyEvent.KEYCODE_DPAD_LEFT -> {
                if (mContentAdapter.showHighLight) {
                    if (mContentAdapter.selected % CONTENT_ROW_SPACE == 0) {
                        mContentAdapter.showHighLight = false
                        mMenuAdapter.showHighLight = true
                    } else {
                        mContentAdapter.selected -= 1
                        content_list.smoothScrollToPosition(mContentAdapter.selected)
                    }
                }
                return true
            }

            KeyEvent.KEYCODE_DPAD_RIGHT -> {
                if (mMenuAdapter.showHighLight) {
                    mMenuAdapter.showHighLight = false
                    mContentAdapter.showHighLight = true
                    mContentAdapter.selected = 0
                    content_list.scrollToPosition(0)
                } else {
                    mContentAdapter.selected += 1
                    content_list.smoothScrollToPosition(mContentAdapter.selected)
                }
                return true
            }

            KeyEvent.KEYCODE_DPAD_CENTER -> {
                if (mContentAdapter.showHighLight) {
                    val course = mContentAdapter.mMovieList.get(mContentAdapter.selected)

                    val intent = Intent(this, CourseActivity::class.java)
                    intent.putExtra("course", course)
                    startActivity(intent)
                } else if (mMenuAdapter.selected == 0) {
                    showSearchFragment()
                }
                return true
            }

            KeyEvent.KEYCODE_BACK -> {
                Log.d(TAG, "onKeyDown: on back click")
                finish()
                return true
            }

            else -> {
                return super.onKeyDown(keyCode, event)
            }
        }
    }

    public fun getCourseData() = mPresenter.getCourseData()

    private fun showSearchFragment() {
//        val searchFrament = MySearchFragment()
//
//        fragmentManager.beginTransaction()
//                .replace(R.id.search_panel, searchFrament)
//                .commit()

        val searchFragment = SearchFragment.newInstance()
        searchFragment.setOnSearchClickListener {
            Log.d(TAG, "showSearchFragment: $it")
        }

        searchFragment.show(supportFragmentManager, "search")
    }


    override fun showMenuList(cnameList: List<String>) {
        mMenuAdapter.cnameList = cnameList
    }
}