package br.com.pokedexproject.ui.view

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.WindowManager
import android.widget.VideoView
import androidx.appcompat.app.AppCompatActivity
import br.com.pokedexproject.R

import java.util.*

class PokeWhoActivity : AppCompatActivity() {



    @Suppress("DEPRECATION")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_poke_who)

        supportActionBar?.hide()

        this.window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        val videoView = findViewById<VideoView>(R.id.videoView)

        fun ClosedRange<Int>.random() =
            Random().nextInt(endInclusive - start) + start

        when ((1..10).random()) {
            1 -> {videoView.setVideoPath("android.resource://" + packageName + "/" + R.raw.bulbasaur)
            }
            2 -> {videoView.setVideoPath("android.resource://" + packageName + "/" + R.raw.caterpie)
            }
            3 -> {videoView.setVideoPath("android.resource://" + packageName + "/" + R.raw.clefairy)
            }
            4 -> {videoView.setVideoPath("android.resource://" + packageName + "/" + R.raw.cubone)
            }
            5 -> {videoView.setVideoPath("android.resource://" + packageName + "/" + R.raw.koffing)
            }
            6 -> {videoView.setVideoPath("android.resource://" + packageName + "/" + R.raw.metapod)
            }
            7 -> {videoView.setVideoPath("android.resource://" + packageName + "/" + R.raw.onix)
            }
            8 -> {videoView.setVideoPath("android.resource://" + packageName + "/" + R.raw.pikachu)
            }
            9 -> {videoView.setVideoPath("android.resource://" + packageName + "/" + R.raw.sandshrew)
            }
            10 -> {videoView.setVideoPath("android.resource://" + packageName + "/" + R.raw.seel)
            }
        }

        videoView.start()

        Handler().postDelayed({
            startActivity(Intent(this, PokeListActivity::class.java))
            finish()
        }, 12000)

    }
}