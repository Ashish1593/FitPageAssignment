package com.example.fitpagetask.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Stocks(
    val id: Int,
    val name: String,
    val tag: String,
    val color: String?,
    val criteria: List<Criteria>): Parcelable
