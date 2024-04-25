package com.example.formacion_android

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.example.formacion_android.api.Network
import com.example.formacion_android.api.PokemonApi
import com.example.formacion_android.databinding.FragmentPokemonDetailsBinding
import com.squareup.picasso.Picasso
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PokemonDetailsFragment : Fragment() {

    private var _binding: FragmentPokemonDetailsBinding? = null
    private val binding get() = _binding!!

    private val args: PokemonDetailsFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val pokemonId = args.pokemonId

        binding.tvName.text = pokemonId
        getSinglePokemon(pokemonId)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPokemonDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    private fun getSinglePokemon(name: String) {
        CoroutineScope(Dispatchers.IO).launch {
            val call = Network.getRetrofit().create(PokemonApi::class.java).getSinglePokemon(name)
            activity?.runOnUiThread {
                Picasso.get().load(call.sprites.front_default).resize(400,0).into(binding.ivPokemon)
                binding.tvHeight.text = (call.height.toFloat() / 10).toString()
                binding.tvWeight.text = (call.weight.toFloat() / 10).toString()
            }
        }
    }
}