package com.example.daggersample.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.example.daggersample.R
import com.example.daggersample.databinding.ActivityMainBinding
import com.example.daggersample.extensions.onEnterPressed
import com.example.daggersample.extensions.showErrorMessage
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        if (savedInstanceState != null) {
            binding.tvOutput.text = savedInstanceState.getString(TV_DATA_OUTPUT_TAG)
            binding.etDataInput.setText(savedInstanceState.getString(ET_DATA_INPUT_TAG))
        }

        configureViews()
        setContentView(binding.root)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.run {
            putString(ET_DATA_INPUT_TAG, binding.etDataInput.text.toString())
            putString(TV_DATA_OUTPUT_TAG, binding.tvOutput.text.toString())
        }

        super.onSaveInstanceState(outState)
    }

    private fun configureViews() {
        with(binding) {
            etDataInput.onEnterPressed {
                when (viewModel.getStudentFromString(etDataInput.text.toString())) {
                    MainViewModel.ValidationResult.ALL_VALID -> {
                        etDataInput.setText(String())
                    }
                    MainViewModel.ValidationResult.NAME -> {
                        showErrorMessage(R.string.mainScreen_nameValidationError)
                    }
                    MainViewModel.ValidationResult.SURNAME -> {
                        showErrorMessage(R.string.mainScreen_surnameValidationError)
                    }
                    MainViewModel.ValidationResult.GRADE -> {
                        showErrorMessage(R.string.mainScreen_gradeValidationError)
                    }
                    MainViewModel.ValidationResult.YEAR -> {
                        showErrorMessage(R.string.mainScreen_birthdateYearValidationError)
                    }
                    MainViewModel.ValidationResult.MISSING_FIELDS -> {
                        showErrorMessage(R.string.mainScreen_fieldCountValidationError)
                    }
                }
            }

            btnShow.setOnClickListener {
                var output = String()
                viewModel.students.forEach {
                    output += "$it \n"
                }
                tvOutput.text = output
            }
        }
    }

    companion object {
        const val ET_DATA_INPUT_TAG = "etInputTag"
        const val TV_DATA_OUTPUT_TAG = "tvOutputTag"
    }
}
