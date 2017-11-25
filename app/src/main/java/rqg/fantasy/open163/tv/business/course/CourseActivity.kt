package rqg.fantasy.open163.tv.business.course

import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_course.*
import kotlinx.android.synthetic.main.item_course.view.*
import rqg.fantasy.open163.tv.BaseActivity
import rqg.fantasy.open163.tv.R
import rqg.fantasy.open163.tv.business.play.PlayActivity
import rqg.fantasy.open163.tv.model.MovieItem
import rqg.fantasy.open163.tv.model.VideoListItem
import javax.inject.Inject

/**
 * * Created by rqg on 14/08/2017.
 */


class CourseActivity : BaseActivity(), CourseContract.View {
    val TAG = "CourseActivity"

    @Inject lateinit var presenter: CourseContract.Presenter
    override lateinit var courseData: MovieItem

    private lateinit var adapter: CourseAdapter


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
                val activityIntent = Intent(this, PlayActivity::class.java)
                activityIntent.putParcelableArrayListExtra("video_list", ArrayList(adapter.playItemList))
                activityIntent.putExtra("index", adapter.selectIndex)
                startActivity(activityIntent)
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


private class CourseAdapter : RecyclerView.Adapter<CourseAdapter.CourseHolder>() {

    var playItemList: List<VideoListItem> = mutableListOf()

    var selectIndex = 0
        set(value) {
            if (value < 0 || value >= itemCount)
                return

            if (value != field) {
                field = value
                notifyDataSetChanged()
            }
        }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): CourseHolder {
        val view = LayoutInflater.from(parent?.context).inflate(R.layout.item_course, parent, false)
        return CourseHolder(view)
    }

    override fun onBindViewHolder(holder: CourseHolder?, position: Int) {
        holder?.let {
            val videoItem = playItemList[position]
            it.image.setImageURI(videoItem.imgpath)
            it.timeLen.text = it.timeLen.context.getString(R.string.course_time_len, videoItem.mlength?.div(60))
            it.title.text = it.title.context.getString(R.string.course_title, position + 1, videoItem.title)

            if (position == selectIndex) {
                it.itemView.setBackgroundResource(R.drawable.round_stroke_white_bg)
            } else {
                it.itemView.setBackgroundResource(R.drawable.transparent_bg)
            }

        }
    }

    override fun getItemCount(): Int {
        return playItemList.size
    }


    class CourseHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title = itemView.course_class_title
        val image = itemView.course_class_img
        val timeLen = itemView.course_class_time_len
    }
}