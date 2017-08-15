package rqg.fantasy.open163.tv.business.course

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.KeyEvent
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_course.*
import rqg.fantasy.open163.tv.BaseActivity
import rqg.fantasy.open163.tv.R
import rqg.fantasy.open163.tv.model.MovieItem
import javax.inject.Inject

/**
 * * Created by rqg on 14/08/2017.
 */


class CourseActivity : BaseActivity(), CourseContract.View {
    val TAG = "CourseActivity"

    @Inject lateinit var presenter: CourseContract.Presenter
    override lateinit var courseData: MovieItem

    lateinit var adapter: CourseAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_course)

        courseData = intent.getParcelableExtra("course")!!

        play_list.setHasFixedSize(true)
        play_list.layoutManager = LinearLayoutManager(this)
        adapter = CourseAdapter()
        play_list.adapter = adapter

        updateCourseInfoUi()

        presenter.start()
        presenter.loadCourseDetail()
    }


    override fun updateCourseInfoUi() {
        course_img.setImageURI(courseData.imgpath)
        course_title.text = courseData.title
        course_author.text = courseData.director
        course_desc.text = courseData.description

        courseData.videoList?.let {
            adapter.playItemList = it.requireNoNulls()
            adapter.notifyDataSetChanged()

        }
        Log.d(TAG, "updateCourseInfoUi: " + courseData.videoList)
    }


    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        when (keyCode) {
            KeyEvent.KEYCODE_DPAD_CENTER -> {
                Log.d(TAG, "onKeyDown: " + adapter.playItemList[adapter.selectIndex])
                return true
            }

            KeyEvent.KEYCODE_DPAD_UP -> {
                adapter.selectIndex--
                play_list.smoothScrollToPosition(adapter.selectIndex)
                return true
            }

            KeyEvent.KEYCODE_DPAD_DOWN -> {
                adapter.selectIndex++
                play_list.smoothScrollToPosition(adapter.selectIndex)
                return true
            }
            else -> {
                return super.onKeyDown(keyCode, event)
            }
        }

    }
}