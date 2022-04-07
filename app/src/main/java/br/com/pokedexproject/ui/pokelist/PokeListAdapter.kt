package br.com.pokedexproject.ui.pokelist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.pokedexproject.R
import br.com.pokedexproject.model.api.PokeResult
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.card_pokemon_search.view.*
import java.util.*

class PokeListAdapter(val pokemonClick: (Int) -> Unit): RecyclerView.Adapter<PokeListAdapter.SearchViewHolder>() {
    var pokemonList: List<PokeResult> = emptyList<PokeResult>()

    fun setData(list: List<PokeResult>){
        pokemonList = list
        this.notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        return SearchViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.card_pokemon_search, parent,false))
    }

    override fun getItemCount(): Int {
        return pokemonList.size
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        val pokemon = pokemonList[position]
        val formattedNumber = (position + 1).toString().padStart(3, '0')
        val imagePokemon = holder.itemView.imagePokemon
        ("Nº $formattedNumber").also { holder.itemView.textViewPokemonNumber.text = it }
        holder.itemView.setOnClickListener { pokemonClick(position + 1) }
        holder.itemView.textViewPokemonName.text = pokemon.name.replaceFirstChar {
            if (it.isLowerCase()) it.titlecase(
                Locale.getDefault()
            ) else it.toString()
        }

        Glide.with(imagePokemon)
            .load("https://assets.pokemon.com/assets/cms2/img/pokedex/detail/${formattedNumber}.png")
            .into(imagePokemon)
    }
    class SearchViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

    }
}