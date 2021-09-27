package com.example.fitpagetask.ui.main.view

import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.fitpagetask.data.model.Stocks
import com.example.fitpagetask.databinding.StocksDetailBinding

class StocksDetailsActivity: AppCompatActivity() {

companion object {
        const val EXTRA = "stocks"
    }
    var stock: Stocks? = null
    private lateinit var binding: StocksDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = StocksDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (intent.extras != null) {
               stock = intent.getParcelableExtra(EXTRA) as Stocks?
            }
        setupUI()
    }

   fun setupUI() {
           binding.tvName.text = stock?.name
           binding.tvTag.text = stock?.tag
           binding.tvTag.setTextColor(if (stock?.color == "green") Color.GREEN else Color.RED)
            var desc = ""
       for(i in 0 until stock?.criteria?.size!!){
           desc += stock?.criteria!![i].text
           if (i < stock?.criteria?.size!! - 1){
               desc += "\n\nand\n\n"
           }
       }
       binding.tvStockDescription.text = desc
   }
}