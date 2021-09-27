package com.example.fitpagetask.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Criteria (
    val type: String,
    val text: String
): Parcelable
