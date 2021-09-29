package com.example.fitpagetask.ui.main.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fitpagetask.data.api.ServiceBuilder
import com.example.fitpagetask.data.model.Stocks
import com.example.fitpagetask.data.repository.StocksRepository
import com.example.fitpagetask.utils.Resource
import com.example.fitpagetask.utils.Status
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect

class MainViewModel : ViewModel() {

    private val repository = StocksRepository(
        ServiceBuilder.apiService()
    )

    val stocks = MutableStateFlow(
        Resource(
            Status.LOADING,
            listOf<Stocks>(), ""
        )
    )

    init { getStocks() }

    fun getStocks() {
        stocks.value = Resource.loading()
        viewModelScope.launch {
            repository.getStocks()
                .catch {
                    stocks.value =
                        Resource.error(it.message.toString())
                }
                .collect {
                    stocks.value = Resource.success(it.data)
                }
        }
    }
}