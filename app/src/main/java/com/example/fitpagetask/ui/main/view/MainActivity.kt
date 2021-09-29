package com.example.fitpagetask.ui.main.view

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fitpagetask.data.model.Stocks
import com.example.fitpagetask.databinding.ActivityMainBinding
import com.example.fitpagetask.ui.main.adapter.MainAdapter
import com.example.fitpagetask.ui.main.viewmodel.MainViewModel
import com.example.fitpagetask.utils.Status
import kotlinx.coroutines.launch
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import kotlinx.coroutines.flow.collect

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: MainAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setContentView(binding.root)
        setupViewModel()
        setupUI()
        setupFlow()
    }

    private fun setupViewModel() {
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

    }

    private fun setupUI() {
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = MainAdapter(arrayListOf())
        binding.recyclerView.adapter = adapter
    }


    private fun setupFlow() {
        //Since flow run asynchronously, start listening on background thread
        lifecycleScope.launch {
            viewModel.stocks.collect {

                when (it.status) {
                    Status.LOADING -> {
                        binding.progressBar.visibility = View.VISIBLE
                        binding.recyclerView.visibility = View.GONE
                    }
                    Status.SUCCESS -> {
                        binding.recyclerView.visibility = View.VISIBLE
                        binding.progressBar.visibility = View.GONE
                        it.data?.let { stocks -> retrieveList(stocks) }
                    }
                    else -> {
                        binding.recyclerView.visibility = View.VISIBLE
                        binding.progressBar.visibility = View.GONE
                        Toast.makeText(this@MainActivity, "${it.message}", Toast.LENGTH_SHORT).show()
                    }
                }


            }

        }
    }

    private fun retrieveList(stocks: List<Stocks>) {
        adapter.apply {
            addStocks(stocks)
            notifyDataSetChanged()
        }
    }
}