package com.example.fitpagetask.ui.main.adapter


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.fitpagetask.databinding.ValuesItemCellBinding

class ValuesAdapter(private val values: ArrayList<Double>) : RecyclerView.Adapter<ValuesAdapter.DataViewHolder>() {

    class DataViewHolder(private val itemLayout: ValuesItemCellBinding) : RecyclerView.ViewHolder(itemLayout.root) {
        fun bind(value: Double) {
            itemView.apply {
                itemLayout.tvValue.text = value.toString()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        DataViewHolder(
            ValuesItemCellBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun getItemCount(): Int = values.size

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder.bind(values[position])
    }

    fun addValues(values: List<Double>) {
        this.values.apply {
            clear()
            addAll(values)
        }
    }
}