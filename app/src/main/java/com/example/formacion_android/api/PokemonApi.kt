package com.example.formacion_android.api

import com.example.formacion_android.model.PokemonResponse
import com.example.formacion_android.model.SinglePokemonResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokemonApi {
    @GET("pokemon/")
    suspend fun getPokemons(
        @Query("limit") limit: Int?,
        @Query("offset") offset: Int?
    ): PokemonResponse

    @GET("pokemon/{name}/")
    suspend fun getSinglePokemon(
        @Path("name") name: String
    ): SinglePokemonResponse
}