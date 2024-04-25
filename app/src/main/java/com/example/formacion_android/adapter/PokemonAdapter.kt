package com.example.formacion_android.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.formacion_android.R
import com.example.formacion_android.databinding.ItemPokemonBinding
import com.example.formacion_android.model.SinglePokemonResponse
import com.squareup.picasso.Picasso

class PokemonAdapter(
    private val pokemons: List<SinglePokemonResponse>
) : RecyclerView.Adapter<PokemonAdapter.PokemonViewHolder>() {

    private lateinit var mListener: onItemClickListener

    interface onItemClickListener{
        fun onItemClick(position: Int, pokemon: SinglePokemonResponse)
    }

    fun setOnItemClickListener(listener: onItemClickListener) {
        mListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return PokemonViewHolder(layoutInflater.inflate(R.layout.item_pokemon, parent, false), mListener, pokemons)
    }

    override fun getItemCount(): Int = pokemons.size

    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {
        val item = pokemons[position]
        holder.bind(item)
    }


    class PokemonViewHolder(
        view: View,
        listener: onItemClickListener,
        pokemons: List<SinglePokemonResponse>
    ): RecyclerView.ViewHolder(view){
        private val binding = ItemPokemonBinding.bind(view)

        init {
            itemView.setOnClickListener {
                listener.onItemClick(adapterPosition, pokemons[adapterPosition])
            }
        }

        fun bind(pokemon: SinglePokemonResponse) {
            binding.tvName.text = pokemon.name
            Picasso.get().load(pokemon.sprites.front_default).resize(400,0).into(binding.ivPokemon)
        }
    }
}