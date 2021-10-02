package com.example.fitpagetask.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class VariableDetail (
    val type: String? = "",
    val study_type: String? = "",
    val parameter_name: String? = "",
    val min_value: Double? = 0.0,
    val max_value: Double? = 0.0,
    val default_value: Double? = 0.0,
    val values: List<Double>? = emptyList()
): Parcelable

