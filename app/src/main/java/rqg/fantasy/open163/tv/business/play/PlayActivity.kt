package rqg.fantasy.open163.tv.business.play

import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.*
import com.google.android.exoplayer2.*
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory
import com.google.android.exoplayer2.source.ExtractorMediaSource
import com.google.android.exoplayer2.source.TrackGroupArray
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector
import com.google.android.exoplayer2.trackselection.TrackSelectionArray
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import com.google.android.exoplayer2.util.Util
import kotlinx.android.synthetic.main.activity_play.*
import kotlinx.android.synthetic.main.item_play_menu.view.*
import rqg.fantasy.open163.tv.BaseActivity
import rqg.fantasy.open163.tv.R
import rqg.fantasy.open163.tv.model.VideoListItem

/**
 * * Created by rqg on 15/08/2017.
 */

class PlayActivity : BaseActivity(), ExoPlayer.EventListener, OnVideoSelectListener {
    private companion object {
        const val TAG = "PlayActivity"
    }

    private lateinit var mainHandler: Handler
    private var bandwidthMeter = DefaultBandwidthMeter()
    private var videoTrackSelectionFactory = AdaptiveTrackSelection.Factory(bandwidthMeter)
    private var trackSelector = DefaultTrackSelector(videoTrackSelectionFactory)
    private lateinit var player: SimpleExoPlayer
    private lateinit var videoList: List<VideoListItem>
    private var currentIndex: Int = 0
    private lateinit var menuAdapter: MenuAdapter
    private lateinit var dsFactory: DefaultDataSourceFactory


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_play)

        mainHandler = Handler(Looper.getMainLooper())

        videoList = intent.getParcelableArrayListExtra("video_list")
        currentIndex = intent.getIntExtra("index", 0)


        initPlayer()

        prepareVideo(videoList[currentIndex])


        menuAdapter = MenuAdapter(videoList, currentIndex, this)
        drawer_left.adapter = menuAdapter
        drawer_left.layoutManager = LinearLayoutManager(this)
        drawer_left.setHasFixedSize(true)


    }

    private fun prepareVideo(videoListItem: VideoListItem) {
        val videoUrl = videoListItem.repovideourlmp4
        val videoSource = ExtractorMediaSource(Uri.parse(videoUrl), dsFactory, DefaultExtractorsFactory(), null, null)
        player.playWhenReady = true
        player.prepare(videoSource)
    }

    private fun initPlayer() {
        dsFactory = DefaultDataSourceFactory(this, Util.getUserAgent(this, "rqg"))
        player = ExoPlayerFactory.newSimpleInstance(this, trackSelector)
        player_view.player = player
        player_view.controllerShowTimeoutMs = 1500
        val playParams = PlaybackParameters(1.0f, 1.0f)
        player.playbackParameters = playParams
        player.addListener(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        player.release()
    }

    override fun onPause() {
        super.onPause()
        player.playWhenReady = false
    }


    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        Log.d(TAG, "onKeyDown: ${KeyEvent.keyCodeToString(keyCode)}")

        return when (keyCode) {
            KeyEvent.KEYCODE_DPAD_CENTER -> {

                if (drawer.isDrawerOpen(drawer_left)) {
                    menuAdapter.performSelectCurrentSelect()
                } else {
                    player_view.showController()
                    player.playWhenReady = !player.playWhenReady
                }

                true
            }
            KeyEvent.KEYCODE_DPAD_LEFT -> {
                player_view.showController()
                player.seekTo(player.currentPosition - 5000)
                true
            }
            KeyEvent.KEYCODE_DPAD_RIGHT -> {
                player_view.showController()
                player.seekTo(player.currentPosition + 5000)
                true
            }
            KeyEvent.KEYCODE_DPAD_UP -> {
                if (drawer.isDrawerOpen(drawer_left)) {
                    menuAdapter.currentIndex--
                    drawer_left.scrollToPosition(menuAdapter.currentIndex)
                } else {
                    val params = PlaybackParameters(player.playbackParameters.speed + 0.25f, 1.0f)
                    player.playbackParameters = params
                }

                true
            }
            KeyEvent.KEYCODE_DPAD_DOWN -> {

                if (drawer.isDrawerOpen(drawer_left)) {
                    menuAdapter.currentIndex++
                    drawer_left.scrollToPosition(menuAdapter.currentIndex)
                } else {
                    val nextSpeed = player.playbackParameters.speed - 0.25f
                    if (nextSpeed <= 0.25f) {
                        return true
                    }
                    val params = PlaybackParameters(nextSpeed, 1.0f)
                    player.playbackParameters = params
                }

                true
            }

            KeyEvent.KEYCODE_MENU -> {
                if (drawer.isDrawerOpen(drawer_left))
                    drawer.closeDrawer(drawer_left)
                else
                    drawer.openDrawer(drawer_left)

                true
            }

            else -> super.onKeyDown(keyCode, event)
        }
    }

    override fun onBackPressed() {
        if (drawer.isDrawerOpen(drawer_left)) {
            drawer.closeDrawer(drawer_left)
        } else {
            super.onBackPressed()
        }

    }

    override fun onVideoSelect(videoListItem: VideoListItem) {
        prepareVideo(videoListItem)
        drawer.closeDrawer(drawer_left)
    }

    override fun onPlaybackParametersChanged(playbackParameters: PlaybackParameters?) {
        playbackParameters?.let {
            runOnUiThread {
                speed_info.alpha = 1f
                speed_info.visibility = View.VISIBLE
                speed_info.text = String.format("x%.2f", it.speed)

                speed_info.animate().alpha(0f)
                        .setStartDelay(1500)
                        .withEndAction {
                            speed_info.visibility = View.INVISIBLE
                        }
                        .start()
            }
        }
    }

    override fun onTracksChanged(trackGroups: TrackGroupArray?, trackSelections: TrackSelectionArray?) {
    }

    override fun onPlayerError(error: ExoPlaybackException?) {
        Log.e(TAG, "onPlayerError: ", error)
    }

    override fun onPlayerStateChanged(playWhenReady: Boolean, playbackState: Int) {

    }

    override fun onLoadingChanged(isLoading: Boolean) {
    }

    override fun onPositionDiscontinuity() {
    }

    override fun onTimelineChanged(timeline: Timeline?, manifest: Any?) {
    }


    private class MenuAdapter(private val videoList: List<VideoListItem>, index: Int, private val onVideoSelectListener: OnVideoSelectListener) : RecyclerView.Adapter<MenuAdapter.MenuHolder>() {

        var currentIndex: Int = 0
            set(value) {
                if (value >= 0 && value < videoList.size)
                    field = value

                notifyDataSetChanged()
            }

        init {
            currentIndex = index
        }

        override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): MenuHolder {
            val view = LayoutInflater.from(parent!!.context).inflate(R.layout.item_play_menu2, parent, false)

            return MenuHolder(view)
        }

        override fun getItemCount(): Int = videoList.size

        override fun onBindViewHolder(holder: MenuHolder?, position: Int) {
            val videoListItem = videoList[position]
            holder!!.name.text = String.format("P%2d %s", position, videoListItem.title)

            if (position == currentIndex) {
                holder.itemView.setBackgroundResource(R.drawable.round_stroke_white_bg)
            } else {
                holder.itemView.setBackgroundResource(R.drawable.transparent_bg)
            }
        }

        fun performSelectCurrentSelect() {
            onVideoSelectListener.onVideoSelect(videoList[currentIndex])
        }


        private class MenuHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            val name = itemView.cname!!
        }
    }
}

private interface OnVideoSelectListener {
    fun onVideoSelect(videoListItem: VideoListItem)
}