package rqg.fantasy.open163.tv.business.play

import android.net.Uri
import android.os.Bundle
import android.os.Handler
import com.google.android.exoplayer2.ExoPlayerFactory
import com.google.android.exoplayer2.PlaybackParameters
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory
import com.google.android.exoplayer2.source.ExtractorMediaSource
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import com.google.android.exoplayer2.util.Util
import kotlinx.android.synthetic.main.activity_play.*
import rqg.fantasy.open163.tv.BaseActivity
import rqg.fantasy.open163.tv.R

/**
 * * Created by rqg on 15/08/2017.
 */

class PlayActivity : BaseActivity() {
    lateinit var mainHandler: Handler
    var bandwidthMeter = DefaultBandwidthMeter()
    var videoTrackSelectionFactory = AdaptiveTrackSelection.Factory(bandwidthMeter)
    var trackSelector = DefaultTrackSelector(videoTrackSelectionFactory)
    lateinit var player: SimpleExoPlayer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_play)

        val videoUrl = intent.getStringExtra("video_url")


        val dsFactory = DefaultDataSourceFactory(this, Util.getUserAgent(this, "rqg"))
        val videoSource = ExtractorMediaSource(Uri.parse(videoUrl), dsFactory, DefaultExtractorsFactory(), null, null)

        player = ExoPlayerFactory.newSimpleInstance(this, trackSelector)
        player_view.player = player

        player.playWhenReady = true
        val playParams = PlaybackParameters(1.4f, 1.0f)
        player.playbackParameters = playParams

        player.prepare(videoSource)


    }

    override fun onDestroy() {
        super.onDestroy()
        player.release()
    }
}