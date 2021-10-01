package com.example.fitpagetask.data.model

data class VariableDetail (
    val type: String,
    val study_type: String,
    val parameter_name: String,
    val min_value: Double,
    val max_value: Double,
    val default_value: Double,
    val values: List<Double>
)

