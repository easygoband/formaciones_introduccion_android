package com.example.formacion_android.data

import com.example.formacion_android.api.Network
import com.example.formacion_android.api.PokemonApi
import com.example.formacion_android.model.PokemonResult
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class PokemonDataSource {

    fun getPokemons(limit: Int = 20, offset: Int = 0): List<PokemonResult> {
        var pokemons = mutableListOf<PokemonResult>()
        CoroutineScope(Dispatchers.IO).launch {
            val call = Network.getRetrofit().create(PokemonApi::class.java).getPokemons(limit, offset)

            try {
                println("Luis Fernando -> $call")
                pokemons.addAll(call.results)
                println("Luis Fernando Ordaz -> $pokemons")
            }catch (e: Exception) {
                println("Luis -> ${e.stackTrace}")
            }
        }
        println("Luis Fernando Ordaz Monreal -> $pokemons")
        return pokemons
    }
}