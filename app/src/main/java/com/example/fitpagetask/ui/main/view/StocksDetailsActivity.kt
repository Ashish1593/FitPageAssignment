package com.example.fitpagetask.ui.main.view

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.view.View
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

    private lateinit var context:Context

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = StocksDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        context = this
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
        setDescriptionWithClickableLink(description)
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
                    desc = desc.replace(key, "(${value.values?.get(0)})", true)
                } else {
                    desc = desc.replace(key, "(${value.default_value})", true)
                }
            }
        }
        return desc
    }

    fun setDescriptionWithClickableLink(description: String) {

        var spannableString = SpannableString(description)

        variableMap.onEachIndexed { index, entry ->

            if (entry.value.type == "value") {
                val tempStr = "(${entry.value.values?.get(0)})"
                if (description.contains(tempStr)) {
                    val startIndex = description.indexOf(tempStr)
                    val endIndex = startIndex + tempStr.length
                    spannableString =
                        makeLinkClickable(startIndex, endIndex, entry, spannableString)
                }
            } else {
                val tempStr = "(${entry.value.default_value})"
                if (description.contains(tempStr)) {
                    val startIndex = description.indexOf(tempStr)
                    val endIndex = startIndex + tempStr.length
                    spannableString =
                        makeLinkClickable(startIndex, endIndex, entry, spannableString)
                }
            }
        }
        binding.tvStockDescription.setLinkTextColor(Color.BLUE)
        binding.tvStockDescription.text = spannableString
        binding.tvStockDescription.movementMethod = LinkMovementMethod.getInstance()

    }

    fun makeLinkClickable(
        startIndex: Int,
        endIndex: Int,
        entry: Map.Entry<String, VariableDetail>,
        description: SpannableString
    ): SpannableString {

        val clickSpan = object : ClickableSpan() {
            override fun onClick(widget: View) {
                if (entry.value.type == "value") {
                    val intent = Intent(context,ValuesActivity::class.java)
                    intent.putExtra(ValuesActivity.EXTRA, entry.value)
                    context.startActivity(intent)
                } else {
                    val intent = Intent(context,SetParameterActivity::class.java)
                    intent.putExtra(SetParameterActivity.EXTRA, entry.value)
                    context.startActivity(intent)
                }
            }
        }

        description.setSpan(clickSpan, startIndex, endIndex, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        return description
    }
}