package com.example.taskcft.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.taskcft.databinding.FragmentRegistrationBinding
import com.example.taskcft.ui.viewmodels.RegistrationState
import com.example.taskcft.ui.viewmodels.RegistrationViewModel
import java.lang.IllegalStateException

class RegistrationFragment : Fragment() {

    private var _binding: FragmentRegistrationBinding? = null
    private val mBinding
        get() = _binding ?: throw IllegalStateException("Can't load view")
    private val viewModel: RegistrationViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRegistrationBinding.inflate(layoutInflater, container, false)
        initUI()
        mBinding.btnSighUp.setOnClickListener {
        }
        return mBinding.root
    }

    private fun initUI() {
        mBinding.btnSighUp.isEnabled = false
        viewModel.registrationState.observe(viewLifecycleOwner) { state ->
            mBinding.edFirstName.onFocusChangeListener =
                View.OnFocusChangeListener { _, hasFocus ->
                    if (!hasFocus) {
                        val error =
                            viewModel.validateFirstName(mBinding.edFirstName.text.toString())
                        mBinding.edLFirstName.error = if (error == null) {
                            null
                        } else {
                            error.error
                        }
                    }
                }
            mBinding.edSurname.onFocusChangeListener =
                View.OnFocusChangeListener { _, hasFocus ->
                    if (!hasFocus) {
                        val error = viewModel.validateSurname(mBinding.edSurname.text.toString())
                        mBinding.edLSurname.error = if (error == null) {
                            null
                        } else {
                            error.error
                        }
                    }
                }
            mBinding.edPassword.onFocusChangeListener =
                View.OnFocusChangeListener { _, hasFocus ->
                    if (!hasFocus) {
                        val error = viewModel.validatePassword(mBinding.edPassword.text.toString())
                        mBinding.edLPassword.error = if (error == null) {
                            null
                        } else {
                            error.error
                        }
                    }
                }

            mBinding.edRepeatPassword.onFocusChangeListener =
                View.OnFocusChangeListener { _, hasFocus ->
                    if (!hasFocus) {
                        val error =
                            viewModel.validateRepeatPassword(mBinding.edRepeatPassword.text.toString())
                        mBinding.edLRepeatPassword.error = if (error == null) {
                            null
                        } else {
                            error.error
                        }

                    }
                }
            mBinding.btnSighUp.isEnabled = viewModel.isButtonActive()
            mBinding.tvDate.text = state.dataOfBirth
            getDate()
            mBinding.btnSighUp.setOnClickListener {
                val action =
                    RegistrationFragmentDirections.actionRegistrationFragmentToMainFragment("${state.firstName?.first} ${state.surname?.first}")
                findNavController().navigate(action)
            }
        }
    }

    fun getDate() {
        mBinding.apply {
            btnDate.setOnClickListener {
                val datePickerFragment = DatePickerFragment()
                val supportFragmentManager = requireActivity().supportFragmentManager
                supportFragmentManager.setFragmentResultListener(
                    "REQUEST_KEY",
                    viewLifecycleOwner
                ) { resultKey, bundle ->
                    if (resultKey == "REQUEST_KEY") {
                        val date = bundle.getString("SELECTED_DATE")
                        viewModel.setDate(date ?: "")
                    }
                }
                datePickerFragment.show(supportFragmentManager, "DatePickerFragment")
            }
        }

    }


}