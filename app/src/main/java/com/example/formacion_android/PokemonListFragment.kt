package com.example.formacion_android

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.formacion_android.adapter.PokemonAdapter
import com.example.formacion_android.api.Network
import com.example.formacion_android.api.PokemonApi
import com.example.formacion_android.data.PokemonDataSource
import com.example.formacion_android.databinding.FragmentPokemonListBinding
import com.example.formacion_android.model.SinglePokemonResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PokemonListFragment : Fragment() {

    private var _binding: FragmentPokemonListBinding? = null
    private val binding get() = _binding!!

    private val pokemonDataSource: PokemonDataSource = PokemonDataSource()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val offset = (0..1282).random()
        getPokemons(offset = offset)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPokemonListBinding.inflate(inflater, container, false)

        return binding.root
    }

    private fun getPokemons(limit: Int = 20, offset: Int = 0) {
        CoroutineScope(Dispatchers.IO).launch {
            val call = Network.getRetrofit().create(PokemonApi::class.java).getPokemons(limit, offset)
            val pokemons = mutableListOf<SinglePokemonResponse>()
            call.results.forEach {pokemon ->
                val singlePokemonCall = Network.getRetrofit().create(PokemonApi::class.java).getSinglePokemon(pokemon.name)
                pokemons.add(singlePokemonCall)
            }
            activity?.runOnUiThread {
                if (pokemons.isNotEmpty()) {
                    val adapter = PokemonAdapter(pokemons)
                    adapter.setOnItemClickListener(object : PokemonAdapter.onItemClickListener{

                        override fun onItemClick(position: Int, pokemon: SinglePokemonResponse) {
                            findNavController().navigate(PokemonListFragmentDirections.actionPokemonListFragmentToPokemonDetailsFragment(pokemonId = pokemon.name))
                        }

                    })
                    binding.rvPokemons.layoutManager = GridLayoutManager(context, 2)
                    binding.rvPokemons.adapter = adapter

                }
            }
        }
    }
}