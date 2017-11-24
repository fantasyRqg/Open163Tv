package rqg.fantasy.open163.tv.business.play

import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.KeyEvent
import android.view.View
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
import rqg.fantasy.open163.tv.BaseActivity
import rqg.fantasy.open163.tv.R

/**
 * * Created by rqg on 15/08/2017.
 */

class PlayActivity : BaseActivity(), ExoPlayer.EventListener {
    private companion object {
        const val TAG = "PlayActivity"
    }

    lateinit var mainHandler: Handler
    var bandwidthMeter = DefaultBandwidthMeter()
    var videoTrackSelectionFactory = AdaptiveTrackSelection.Factory(bandwidthMeter)
    var trackSelector = DefaultTrackSelector(videoTrackSelectionFactory)
    lateinit var player: SimpleExoPlayer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_play)

        mainHandler = Handler(Looper.getMainLooper())

        val videoUrl = intent.getStringExtra("video_url")

        val dsFactory = DefaultDataSourceFactory(this, Util.getUserAgent(this, "rqg"))
        val videoSource = ExtractorMediaSource(Uri.parse(videoUrl), dsFactory, DefaultExtractorsFactory(), null, null)

        player = ExoPlayerFactory.newSimpleInstance(this, trackSelector)
        player_view.player = player
        player_view.controllerShowTimeoutMs = 1500

        player.playWhenReady = true
        val playParams = PlaybackParameters(1.0f, 1.0f)
        player.playbackParameters = playParams

        player.addListener(this)
        player.prepare(videoSource)

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
                player_view.showController()
                player.playWhenReady = !player.playWhenReady
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
                val params = PlaybackParameters(player.playbackParameters.speed + 0.25f, 1.0f)
                player.playbackParameters = params
                true
            }
            KeyEvent.KEYCODE_DPAD_DOWN -> {
                val params = PlaybackParameters(player.playbackParameters.speed - 0.25f, 1.0f)
                player.playbackParameters = params
                true
            }

            KeyEvent.KEYCODE_MENU -> {

                true
            }

            else -> super.onKeyDown(keyCode, event)
        }
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
}