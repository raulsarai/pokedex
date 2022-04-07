package br.com.pokedexproject.ui.view

import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import br.com.pokedexproject.R
import br.com.pokedexproject.ui.pokeinfo.PokeInfoViewModel
import com.bumptech.glide.Glide
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.activity_pokeinfo.*
import java.util.*

class PokeInfoActivity : AppCompatActivity() {

    private lateinit var viewModel: PokeInfoViewModel
    private var mediaPlayer: MediaPlayer? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pokeinfo)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.title = "PokeInfo"

        val fabRandom = findViewById<FloatingActionButton>(R.id.fabRandom)
        fabRandom.setOnClickListener {
            randomPokemon()
        }
        viewModel = ViewModelProvider(this).get(PokeInfoViewModel::class.java)
        initUI()

    }

    private fun play() {
        mediaPlayer?.start()

    }

    override fun onDestroy() {
        super.onDestroy()
        if (mediaPlayer?.isPlaying == true) {
            mediaPlayer?.stop()
        }
    }

    private fun randomPokemon() {
        fun ClosedRange<Int>.random() =
            Random().nextInt(endInclusive - start) + start

        val pokeRandom = (1..151).random()
        val intent = Intent(this, PokeInfoActivity::class.java)
        intent.putExtra("id", pokeRandom)
        startActivity(intent)
        finish()
    }

    override fun onBackPressed() {
        startActivity(
            Intent(
                this,
                PokeListActivity::class.java
            )
        )
        finishAffinity()
        return
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                startActivity(
                    Intent(
                        this,
                        PokeListActivity::class.java
                    )
                )
                finishAffinity()
            }
        }
        return true
    }

    private fun initUI() {
        val id = intent.extras?.get("id") as Int
        val formattedNumber = id.toString().padStart(3, '0')

        viewModel.getPokemonInfo(id)
        viewModel.pokemonInfo.observe(this) { pokemon ->
            textViewPokemonNameInfo.text = pokemon.name.replaceFirstChar {
                if (it.isLowerCase()) it.titlecase(
                    Locale.getDefault()
                ) else it.toString()
            }
            "${pokemon.weight / 10.0}Kg".also { textViewPokemonHeightInfo.text = it }
            if (pokemon.height / 10.0 < 1.0) {
                "${pokemon.height}0cm".also { textViewPokemonWidthInfo.text = it }
            } else {
                "${pokemon.height / 10.0}m".also { textViewPokemonWidthInfo.text = it }
            }
            "N° $formattedNumber".also { id_number.text = it }
            pokeSounds()
            Glide.with(this)
                .load("https://assets.pokemon.com/assets/cms2/img/pokedex/detail/${formattedNumber}.png")
                .into(imageView)
        }

    }

    private fun pokeSounds() {

        when (id_number.text) {
            "N° 001" -> {
                mediaPlayer = MediaPlayer.create(this, R.raw.bulbasaursound)
                play()
            }
            "N° 002" -> {
                mediaPlayer = MediaPlayer.create(this, R.raw.ivysaur)
                play()
            }
            "N° 003" -> {
                mediaPlayer = MediaPlayer.create(this, R.raw.venusaur)
                play()
            }
            "N° 004" -> {
                mediaPlayer = MediaPlayer.create(this, R.raw.charmander)
                play()
            }
            "N° 005" -> {
                mediaPlayer = MediaPlayer.create(this, R.raw.charmeleon)
                play()
            }
            "N° 006" -> {
                mediaPlayer = MediaPlayer.create(this, R.raw.charizard)
                play()
            }
            "N° 007" -> {
                mediaPlayer = MediaPlayer.create(this, R.raw.squirtle)
                play()
            }
            "N° 008" -> {
                mediaPlayer = MediaPlayer.create(this, R.raw.wartortle)
                play()
            }
            "N° 009" -> {
                mediaPlayer = MediaPlayer.create(this, R.raw.blastoise)
                play()
            }
            "N° 010" -> {
                mediaPlayer = MediaPlayer.create(this, R.raw.caterpiesound)
                play()
            }
            "N° 011" -> {
                mediaPlayer = MediaPlayer.create(this, R.raw.metapodsound)
                play()
            }
            "N° 012" -> {
                mediaPlayer = MediaPlayer.create(this, R.raw.butterfree)
                play()
            }
            "N° 013" -> {
                mediaPlayer = MediaPlayer.create(this, R.raw.weedle)
                play()
            }
            "N° 014" -> {
                mediaPlayer = MediaPlayer.create(this, R.raw.kakuna)
                play()
            }
            "N° 015" -> {
                mediaPlayer = MediaPlayer.create(this, R.raw.beedrill)
                play()
            }
            "N° 016" -> {
                mediaPlayer = MediaPlayer.create(this, R.raw.pidgey)
                play()
            }
            "N° 017" -> {
                mediaPlayer = MediaPlayer.create(this, R.raw.pidgeotto)
                play()
            }
            "N° 018" -> {
                mediaPlayer = MediaPlayer.create(this, R.raw.pidgeot)
                play()
            }
            "N° 019" -> {
                mediaPlayer = MediaPlayer.create(this, R.raw.rattata)
                play()
            }
            "N° 020" -> {
                mediaPlayer = MediaPlayer.create(this, R.raw.raticate)
                play()
            }
            "N° 021" -> {
                mediaPlayer = MediaPlayer.create(this, R.raw.spearow)
                play()
            }
            "N° 022" -> {
                mediaPlayer = MediaPlayer.create(this, R.raw.fearow)
                play()
            }
            "N° 023" -> {
                mediaPlayer = MediaPlayer.create(this, R.raw.ekans)
                play()
            }
            "N° 024" -> {
                mediaPlayer = MediaPlayer.create(this, R.raw.arbok)
                play()
            }
            "N° 025" -> {
                mediaPlayer = MediaPlayer.create(this, R.raw.pikachusound)
                play()
            }
            "N° 026" -> {
                mediaPlayer = MediaPlayer.create(this, R.raw.raichu)
                play()
            }
            "N° 027" -> {
                mediaPlayer = MediaPlayer.create(this, R.raw.sandshrewsound)
                play()
            }
            "N° 028" -> {
                mediaPlayer = MediaPlayer.create(this, R.raw.sandslash)
                play()
            }
            "N° 029" -> {
                mediaPlayer = MediaPlayer.create(this, R.raw.nidoran)
                play()
            }
            "N° 030" -> {
                mediaPlayer = MediaPlayer.create(this, R.raw.nidoran)
                play()
            }
            "N° 031" -> {
                mediaPlayer = MediaPlayer.create(this, R.raw.nidoqueen)
                play()
            }
            "N° 032" -> {
                mediaPlayer = MediaPlayer.create(this, R.raw.nidoran_m)
                play()
            }
            "N° 033" -> {
                mediaPlayer = MediaPlayer.create(this, R.raw.nidoran_m)
                play()
            }
            "N° 034" -> {
                mediaPlayer = MediaPlayer.create(this, R.raw.nidoking)
                play()
            }
            "N° 035" -> {
                mediaPlayer = MediaPlayer.create(this, R.raw.clefairysound)
                play()
            }
            "N° 036" -> {
                mediaPlayer = MediaPlayer.create(this, R.raw.clefable)
                play()
            }
            "N° 037" -> {
                mediaPlayer = MediaPlayer.create(this, R.raw.vulpix)
                play()
            }
            "N° 038" -> {
                mediaPlayer = MediaPlayer.create(this, R.raw.ninetales)
                play()
            }
            "N° 039" -> {
                mediaPlayer = MediaPlayer.create(this, R.raw.jigglypuff)
                play()
            }
            "N° 040" -> {
                mediaPlayer = MediaPlayer.create(this, R.raw.wigglytuff)
                play()
            }
            "N° 041" -> {
                mediaPlayer = MediaPlayer.create(this, R.raw.zubat)
                play()
            }
            "N° 042" -> {
                mediaPlayer = MediaPlayer.create(this, R.raw.golbat)
                play()
            }
            "N° 043" -> {
                mediaPlayer = MediaPlayer.create(this, R.raw.oddish)
                play()
            }
            "N° 044" -> {
                mediaPlayer = MediaPlayer.create(this, R.raw.gloom)
                play()
            }
            "N° 045" -> {
                mediaPlayer = MediaPlayer.create(this, R.raw.vileplume)
                play()
            }
            "N° 046" -> {
                mediaPlayer = MediaPlayer.create(this, R.raw.paras)
                play()
            }
            "N° 047" -> {
                mediaPlayer = MediaPlayer.create(this, R.raw.parasect)
                play()
            }
            "N° 048" -> {
                mediaPlayer = MediaPlayer.create(this, R.raw.venonat)
                play()
            }
            "N° 049" -> {
                mediaPlayer = MediaPlayer.create(this, R.raw.venomoth)
                play()
            }
            "N° 050" -> {
                mediaPlayer = MediaPlayer.create(this, R.raw.diglett)
                play()
            }
            "N° 051" -> {
                mediaPlayer = MediaPlayer.create(this, R.raw.dugtrio)
                play()
            }
            "N° 052" -> {
                mediaPlayer = MediaPlayer.create(this, R.raw.meowth)
                play()
            }
            "N° 053" -> {
                mediaPlayer = MediaPlayer.create(this, R.raw.persian)
                play()
            }
            "N° 054" -> {
                mediaPlayer = MediaPlayer.create(this, R.raw.psyduck)
                play()
            }
            "N° 055" -> {
                mediaPlayer = MediaPlayer.create(this, R.raw.golduck)
                play()
            }
            "N° 056" -> {
                mediaPlayer = MediaPlayer.create(this, R.raw.mankey)
                play()
            }
            "N° 057" -> {
                mediaPlayer = MediaPlayer.create(this, R.raw.primeape)
                play()
            }
            "N° 058" -> {
                mediaPlayer = MediaPlayer.create(this, R.raw.growlithe)
                play()
            }
            "N° 059" -> {
                mediaPlayer = MediaPlayer.create(this, R.raw.arcanine)
                play()
            }
            "N° 060" -> {
                mediaPlayer = MediaPlayer.create(this, R.raw.poliwag)
                play()
            }
            "N° 061" -> {
                mediaPlayer = MediaPlayer.create(this, R.raw.poliwhirl)
                play()
            }
            "N° 062" -> {
                mediaPlayer = MediaPlayer.create(this, R.raw.poliwrath)
                play()
            }
            "N° 063" -> {
                mediaPlayer = MediaPlayer.create(this, R.raw.abra)
                play()
            }
            "N° 064" -> {
                mediaPlayer = MediaPlayer.create(this, R.raw.kadabra)
                play()
            }
            "N° 065" -> {
                mediaPlayer = MediaPlayer.create(this, R.raw.alakazam)
                play()
            }
            "N° 066" -> {
                mediaPlayer = MediaPlayer.create(this, R.raw.machop)
                play()
            }
            "N° 067" -> {
                mediaPlayer = MediaPlayer.create(this, R.raw.machoke)
                play()
            }
            "N° 068" -> {
                mediaPlayer = MediaPlayer.create(this, R.raw.machamp)
                play()
            }
            "N° 069" -> {
                mediaPlayer = MediaPlayer.create(this, R.raw.bellsprout)
                play()
            }
            "N° 070" -> {
                mediaPlayer = MediaPlayer.create(this, R.raw.weepinbell)
                play()
            }
            "N° 071" -> {
                mediaPlayer = MediaPlayer.create(this, R.raw.victreebel)
                play()
            }
            "N° 072" -> {
                mediaPlayer = MediaPlayer.create(this, R.raw.tentacool)
                play()
            }
            "N° 073" -> {
                mediaPlayer = MediaPlayer.create(this, R.raw.tentacruel)
                play()
            }
            "N° 074" -> {
                mediaPlayer = MediaPlayer.create(this, R.raw.geodude)
                play()
            }
            "N° 075" -> {
                mediaPlayer = MediaPlayer.create(this, R.raw.graveler)
                play()
            }
            "N° 076" -> {
                mediaPlayer = MediaPlayer.create(this, R.raw.golem)
                play()
            }
            "N° 077" -> {
                mediaPlayer = MediaPlayer.create(this, R.raw.ponyta)
                play()
            }
            "N° 078" -> {
                mediaPlayer = MediaPlayer.create(this, R.raw.rapidash)
                play()
            }
            "N° 079" -> {
                mediaPlayer = MediaPlayer.create(this, R.raw.slowpoke)
                play()
            }
            "N° 080" -> {
                mediaPlayer = MediaPlayer.create(this, R.raw.slowbro)
                play()
            }
            "N° 081" -> {
                mediaPlayer = MediaPlayer.create(this, R.raw.magnemite)
                play()
            }
            "N° 082" -> {
                mediaPlayer = MediaPlayer.create(this, R.raw.magneton)
                play()
            }
            "N° 083" -> {
                mediaPlayer = MediaPlayer.create(this, R.raw.farftechd)
                play()
            }
            "N° 084" -> {
                mediaPlayer = MediaPlayer.create(this, R.raw.doduo)
                play()
            }
            "N° 085" -> {
                mediaPlayer = MediaPlayer.create(this, R.raw.dodrio)
                play()
            }
            "N° 086" -> {
                mediaPlayer = MediaPlayer.create(this, R.raw.seelsound)
                play()
            }
            "N° 087" -> {
                mediaPlayer = MediaPlayer.create(this, R.raw.dewgong)
                play()
            }
            "N° 088" -> {
                mediaPlayer = MediaPlayer.create(this, R.raw.grimer)
                play()
            }
            "N° 089" -> {
                mediaPlayer = MediaPlayer.create(this, R.raw.muk)
                play()
            }
            "N° 090" -> {
                mediaPlayer = MediaPlayer.create(this, R.raw.shellder)
                play()
            }
            "N° 091" -> {
                mediaPlayer = MediaPlayer.create(this, R.raw.cloyster)
                play()
            }
            "N° 092" -> {
                mediaPlayer = MediaPlayer.create(this, R.raw.gastly)
                play()
            }
            "N° 093" -> {
                mediaPlayer = MediaPlayer.create(this, R.raw.haunter)
                play()
            }
            "N° 094" -> {
                mediaPlayer = MediaPlayer.create(this, R.raw.gengar)
                play()
            }
            "N° 095" -> {
                mediaPlayer = MediaPlayer.create(this, R.raw.onixsound)
                play()
            }
            "N° 096" -> {
                mediaPlayer = MediaPlayer.create(this, R.raw.drowzee)
                play()
            }
            "N° 097" -> {
                mediaPlayer = MediaPlayer.create(this, R.raw.hypno)
                play()
            }
            "N° 098" -> {
                mediaPlayer = MediaPlayer.create(this, R.raw.krabby)
                play()
            }
            "N° 099" -> {
                mediaPlayer = MediaPlayer.create(this, R.raw.kingler)
                play()
            }
            "N° 100" -> {
                mediaPlayer = MediaPlayer.create(this, R.raw.voltorb)
                play()
            }
            "N° 101" -> {
                mediaPlayer = MediaPlayer.create(this, R.raw.electrode)
                play()
            }
            "N° 102" -> {
                mediaPlayer = MediaPlayer.create(this, R.raw.exeggcute)
                play()
            }
            "N° 103" -> {
                mediaPlayer = MediaPlayer.create(this, R.raw.exeggutor)
                play()
            }
            "N° 104" -> {
                mediaPlayer = MediaPlayer.create(this, R.raw.cubonesound)
                play()
            }
            "N° 105" -> {
                mediaPlayer = MediaPlayer.create(this, R.raw.marowak)
                play()
            }
            "N° 106" -> {
                mediaPlayer = MediaPlayer.create(this, R.raw.hitmonlee)
                play()
            }
            "N° 107" -> {
                mediaPlayer = MediaPlayer.create(this, R.raw.hitmonchan)
                play()
            }
            "N° 108" -> {
                mediaPlayer = MediaPlayer.create(this, R.raw.lickitung)
                play()
            }
            "N° 109" -> {
                mediaPlayer = MediaPlayer.create(this, R.raw.koffingsound)
                play()
            }
            "N° 110" -> {
                mediaPlayer = MediaPlayer.create(this, R.raw.weezing)
                play()
            }
            "N° 111" -> {
                mediaPlayer = MediaPlayer.create(this, R.raw.rhyhorn)
                play()
            }
            "N° 112" -> {
                mediaPlayer = MediaPlayer.create(this, R.raw.rhydon)
                play()
            }
            "N° 113" -> {
                mediaPlayer = MediaPlayer.create(this, R.raw.chansey)
                play()
            }
            "N° 114" -> {
                mediaPlayer = MediaPlayer.create(this, R.raw.tangela)
                play()
            }
            "N° 115" -> {
                mediaPlayer = MediaPlayer.create(this, R.raw.kangaskhan)
                play()
            }
            "N° 116" -> {
                mediaPlayer = MediaPlayer.create(this, R.raw.horsea)
                play()
            }
            "N° 117" -> {
                mediaPlayer = MediaPlayer.create(this, R.raw.seadra)
                play()
            }
            "N° 118" -> {
                mediaPlayer = MediaPlayer.create(this, R.raw.goldeen)
                play()
            }
            "N° 119" -> {
                mediaPlayer = MediaPlayer.create(this, R.raw.seaking)
                play()
            }
            "N° 120" -> {
                mediaPlayer = MediaPlayer.create(this, R.raw.staryu)
                play()
            }
            "N° 121" -> {
                mediaPlayer = MediaPlayer.create(this, R.raw.starmine)
                play()
            }
            "N° 122" -> {
                mediaPlayer = MediaPlayer.create(this, R.raw.mrmime)
                play()
            }
            "N° 123" -> {
                mediaPlayer = MediaPlayer.create(this, R.raw.scyther)
                play()
            }
            "N° 124" -> {
                mediaPlayer = MediaPlayer.create(this, R.raw.jynx)
                play()
            }
            "N° 125" -> {
                mediaPlayer = MediaPlayer.create(this, R.raw.electrabuzz)
                play()
            }
            "N° 126" -> {
                mediaPlayer = MediaPlayer.create(this, R.raw.magmar)
                play()
            }
            "N° 127" -> {
                mediaPlayer = MediaPlayer.create(this, R.raw.pinsir)
                play()
            }
            "N° 128" -> {
                mediaPlayer = MediaPlayer.create(this, R.raw.tauros)
                play()
            }
            "N° 129" -> {
                mediaPlayer = MediaPlayer.create(this, R.raw.magikarp)
                play()
            }
            "N° 130" -> {
                mediaPlayer = MediaPlayer.create(this, R.raw.gyarados)
                play()
            }
            "N° 131" -> {
                mediaPlayer = MediaPlayer.create(this, R.raw.lapras)
                play()
            }
            "N° 132" -> {
                mediaPlayer = MediaPlayer.create(this, R.raw.ditto)
                play()
            }
            "N° 133" -> {
                mediaPlayer = MediaPlayer.create(this, R.raw.eevee)
                play()
            }
            "N° 134" -> {
                mediaPlayer = MediaPlayer.create(this, R.raw.vaporeon)
                play()
            }
            "N° 135" -> {
                mediaPlayer = MediaPlayer.create(this, R.raw.jolteon)
                play()
            }
            "N° 136" -> {
                mediaPlayer = MediaPlayer.create(this, R.raw.flareon)
                play()
            }
            "N° 137" -> {
                mediaPlayer = MediaPlayer.create(this, R.raw.porygon)
                play()
            }
            "N° 138" -> {
                mediaPlayer = MediaPlayer.create(this, R.raw.omanyte)
                play()
            }
            "N° 139" -> {
                mediaPlayer = MediaPlayer.create(this, R.raw.omastar)
                play()
            }
            "N° 140" -> {
                mediaPlayer = MediaPlayer.create(this, R.raw.kabuto)
                play()
            }
            "N° 141" -> {
                mediaPlayer = MediaPlayer.create(this, R.raw.kabutops)
                play()
            }
            "N° 142" -> {
                mediaPlayer = MediaPlayer.create(this, R.raw.aerodactyl)
                play()
            }
            "N° 143" -> {
                mediaPlayer = MediaPlayer.create(this, R.raw.snorlax)
                play()
            }
            "N° 144" -> {
                mediaPlayer = MediaPlayer.create(this, R.raw.articuno)
                play()
            }
            "N° 145" -> {
                mediaPlayer = MediaPlayer.create(this, R.raw.zapdos)
                play()
            }
            "N° 146" -> {
                mediaPlayer = MediaPlayer.create(this, R.raw.moltres)
                play()
            }
            "N° 147" -> {
                mediaPlayer = MediaPlayer.create(this, R.raw.moltres)
                play()
            }
            "N° 148" -> {
                mediaPlayer = MediaPlayer.create(this, R.raw.moltres)
                play()
            }
            "N° 149" -> {
                mediaPlayer = MediaPlayer.create(this, R.raw.drogonite)
                play()
            }
            "N° 150" -> {
                mediaPlayer = MediaPlayer.create(this, R.raw.mewtwo)
                play()
            }
            "N° 151" -> {
                mediaPlayer = MediaPlayer.create(this, R.raw.mew)
                play()
            }

        }
    }

}