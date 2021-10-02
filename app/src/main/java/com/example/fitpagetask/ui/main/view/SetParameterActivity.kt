package com.example.fitpagetask.ui.main.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.fitpagetask.data.model.VariableDetail
import com.example.fitpagetask.databinding.ParameterLayoutBinding
import java.util.*

class SetParameterActivity : AppCompatActivity() {

    companion object {
        const val EXTRA = "paramters"
    }

    var variable: VariableDetail? = null
    private lateinit var binding: ParameterLayoutBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ParameterLayoutBinding.inflate(layoutInflater)
        setContentView(binding.root)
        if (intent.extras != null) {
            variable = intent.getParcelableExtra(EXTRA) as VariableDetail?
        }
        setupUI()
    }

    fun setupUI() {
        binding.tvParamterName.text = variable?.parameter_name?.lowercase(Locale.getDefault())
            ?.replaceFirstChar {
                if (it.isLowerCase()) it.titlecase(
                    Locale.getDefault()
                ) else it.toString()
            }
        binding.tvStudyType.text = variable?.study_type?.uppercase()
        binding.etParameter.setText(variable?.default_value?.toString())
    }
}