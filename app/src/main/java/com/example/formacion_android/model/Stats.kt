package com.example.formacion_android.model

import android.os.Parcelable
import com.example.formacion_android.model.Stat
import kotlinx.parcelize.Parcelize

@Parcelize
data class Stats(
    val base_stat: Int,
    val effort: Int,
    val stat: Stat
) : Parcelable