package com.example.fitpagetask.ui.main.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fitpagetask.data.model.VariableDetail
import com.example.fitpagetask.databinding.ValuesLayoutBinding
import com.example.fitpagetask.ui.main.adapter.ValuesAdapter

class ValuesActivity:AppCompatActivity() {

    companion object {
        const val EXTRA = "values"
    }

    private lateinit var adapter: ValuesAdapter
    var variable: VariableDetail? = null
    private lateinit var binding: ValuesLayoutBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ValuesLayoutBinding.inflate(layoutInflater)
        setContentView(binding.root)
        if (intent.extras != null) {
            variable = intent.getParcelableExtra(EXTRA) as VariableDetail?
        }
        setupUI()
    }

    fun setupUI() {
        binding.rvValues.layoutManager = LinearLayoutManager(this)
        adapter = ValuesAdapter(arrayListOf())
        binding.rvValues.adapter = adapter
        retrieveList(variable!!.values)
    }

    private fun retrieveList(values: List<Double>?) {
        adapter.apply {
            values?.let { addValues(it) }
            notifyDataSetChanged()
        }
    }
}