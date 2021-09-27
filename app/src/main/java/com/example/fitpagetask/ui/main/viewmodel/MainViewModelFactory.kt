package com.example.fitpagetask.ui.main.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.fitpagetask.data.api.ApiHelper
import com.example.fitpagetask.data.repository.StocksRepository

class MainViewModelFactory(private val apiHelper: ApiHelper) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(StocksRepository(apiHelper)) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }
}