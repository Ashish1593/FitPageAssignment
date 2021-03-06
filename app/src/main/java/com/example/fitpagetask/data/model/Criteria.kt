package com.example.fitpagetask.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue

@Parcelize
data class Criteria (
    val type: String,
    val text: String,
    var variable: @RawValue Any? = null

): Parcelable
