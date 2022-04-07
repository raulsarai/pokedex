package br.com.pokedexproject.ui.view

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.pokedexproject.R
import br.com.pokedexproject.ui.pokelist.PokeListAdapter
import br.com.pokedexproject.ui.pokelist.PokeListViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.activity_pokelist.*
import java.util.*

class PokeListActivity : AppCompatActivity() {

    lateinit var viewModel: PokeListViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pokelist)

        supportActionBar?.title = getString(R.string.supp_actbar_title)
        val fabRandom = findViewById<FloatingActionButton>(R.id.fabRandom)
        fabRandom.setOnClickListener {
            randomPokemon()
        }

        viewModel = ViewModelProvider(this).get(PokeListViewModel::class.java)
        initUI()
    }

    private fun randomPokemon() {
        fun ClosedRange<Int>.random() =
            Random().nextInt(endInclusive - start) + start
        val pokeRandom = (1..151).random()
        val intent = Intent(baseContext, PokeInfoActivity::class.java)
        intent.putExtra("id", pokeRandom)
        startActivity(intent)
        finish()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return true
    }



    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.search -> {
            true
        }
        else -> {
            super.onOptionsItemSelected(item)
        }
    }


    private fun initUI() {

        pokeListRecyclerView.layoutManager = LinearLayoutManager(this)
        pokeListRecyclerView.adapter = PokeListAdapter {
            val intent = Intent(this, PokeInfoActivity::class.java)
            intent.putExtra("id", it)
            startActivity(intent)
        }

        viewModel.getPokemonList()
        viewModel.pokemonList.observe(this, androidx.lifecycle.Observer { list ->
            (pokeListRecyclerView.adapter as PokeListAdapter).setData(list)
        })
    }
}