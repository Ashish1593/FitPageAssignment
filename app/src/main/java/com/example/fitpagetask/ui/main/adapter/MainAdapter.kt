package com.example.fitpagetask.ui.main.adapter

import android.content.Intent
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.fitpagetask.data.model.Stocks
import com.example.fitpagetask.databinding.RecyclerViewCellBinding
import com.example.fitpagetask.ui.main.view.StocksDetailsActivity

class MainAdapter(private val stocks: ArrayList<Stocks>) : RecyclerView.Adapter<MainAdapter.DataViewHolder>() {

    class DataViewHolder(private val itemLayout: RecyclerViewCellBinding) : RecyclerView.ViewHolder(itemLayout.root) {

        fun bind(stocks: Stocks) {
            itemView.apply {
                itemLayout.tvName.text = stocks.name
                itemLayout.tvTag.text = stocks.tag
                itemLayout.tvTag.setTextColor(if (stocks.color == "green") Color.GREEN else Color.RED)
                itemLayout.root.setOnClickListener(View.OnClickListener {
                   val openStocksDetailsIntent = Intent(context,StocksDetailsActivity::class.java)
                    openStocksDetailsIntent.putExtra(StocksDetailsActivity.EXTRA, stocks)
                    context.startActivity(openStocksDetailsIntent)
                })
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        DataViewHolder(
            RecyclerViewCellBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun getItemCount(): Int = stocks.size

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder.bind(stocks[position])
    }

    fun addStocks(stocks: List<Stocks>) {
        this.stocks.apply {
            clear()
            addAll(stocks)
        }

    }
}