package rqg.fantasy.open163.tv.business.course

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.item_course.view.*
import rqg.fantasy.open163.tv.R
import rqg.fantasy.open163.tv.model.VideoListItem

/**
 * * Created by rqg on 15/08/2017.
 */

class CourseAdapter : RecyclerView.Adapter<CourseAdapter.CourseHolder>() {

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