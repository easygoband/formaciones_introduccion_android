package com.example.formacion_android.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class SinglePokemonResponse(
    val sprites: Sprites,
    val stats: List<Stats>,
    val height: Int,
    val weight: Int,
    val name: String
) : Parcelable
