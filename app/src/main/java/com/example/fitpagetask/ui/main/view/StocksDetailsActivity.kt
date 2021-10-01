package com.example.fitpagetask.ui.main.view

import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.fitpagetask.data.model.Stocks
import com.example.fitpagetask.data.model.VariableDetail
import com.example.fitpagetask.databinding.StocksDetailBinding
import com.google.gson.Gson

class StocksDetailsActivity : AppCompatActivity() {

    companion object {
        const val EXTRA = "stocks"
    }

    var stock: Stocks? = null
    private lateinit var binding: StocksDetailBinding
    val variableMap = mutableMapOf<String, VariableDetail>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = StocksDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (intent.extras != null) {
            stock = intent.getParcelableExtra(EXTRA) as Stocks?
        }
        getVariables()
        setupUI()
    }

    fun getVariables() {
        for (criteria in stock?.criteria!!) {
            val json = criteria.variable
            if (json != null) {
                for ((key, value) in json as Map<*, *>) {
                    val gson = Gson()
                    val mapData = gson.toJson(value)
                    val variabledata = gson.fromJson(mapData, VariableDetail::class.java)
                    variableMap.put(key.toString(), variabledata)
                }
            }
        }
    }

    fun setupUI() {
        binding.tvName.text = stock?.name
        binding.tvTag.text = stock?.tag
        binding.tvTag.setTextColor(if (stock?.color == "green") Color.GREEN else Color.RED)
        val description = getDescription()
        binding.tvStockDescription.text = description
    }

    fun getDescription(): String {
        var desc = ""
        for (i in 0 until stock?.criteria?.size!!) {
            desc += stock?.criteria!![i].text
            if (i < stock?.criteria?.size!! - 1) {
                desc += "\n\nand\n\n"
            }
        }
        for ((key, value) in variableMap) {
            if (desc.contains(key)) {
                if (value.type == "value") {
                    desc = desc.replace(key, value.values[0].toString(), true)
                } else {
                    desc = desc.replace(key, value.default_value.toString(), true)
                }
            }
        }
        return desc
    }
}