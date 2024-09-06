package net.thebookofcode.www.audioplayer

import android.media.MediaPlayer
import android.os.Bundle
import android.view.View
import android.widget.SeekBar
import androidx.appcompat.app.AppCompatActivity
import net.thebookofcode.www.audioplayer.databinding.ActivityMainBinding
import kotlin.math.ceil

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    var isPlaying: Boolean = false
    var player: MediaPlayer? = null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.fabPlayOrPause.setOnClickListener {
            if (isPlaying) {
                if (player != null){
                    player!!.pause()
                    isPlaying = false
                    binding.fabPlayOrPause.setImageResource(R.drawable.ic_play)
                }
            } else {
                if (player == null){
                    player = MediaPlayer.create(this, R.raw.song)
                }
                player!!.start()
                isPlaying = true
                with(binding) {
                    fabPlayOrPause.setImageResource(R.drawable.ic_pause)
                    fabStop.visibility = View.VISIBLE
                }

            }
        }

        binding.fabStop.setOnClickListener {
            stopPlayer()
            binding.fabStop.visibility = View.GONE
        }

        player?.setOnCompletionListener {
            stopPlayer()
        }
    }

    private fun stopPlayer() {
        if (player != null) {
            player!!.release()
            player = null
        }
        isPlaying = false
        binding.fabPlayOrPause.setImageResource(R.drawable.ic_play)
    }

    override fun onStop() {
        super.onStop()
        stopPlayer()
    }
}