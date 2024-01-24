package com.example.taskcft.ui.fragments

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.navigation.fragment.navArgs
import com.example.taskcft.R
import com.example.taskcft.databinding.FragmentMainBinding
import java.lang.IllegalStateException

class MainFragment : Fragment() {
    private var _binding: FragmentMainBinding? = null
    private val mBinding
        get() = _binding ?: throw IllegalStateException("Can't load view")
    private val mainArgs : MainFragmentArgs by navArgs()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(layoutInflater, container, false)
        mBinding.btnHello.setOnClickListener {
            val alertDialog = AlertDialog.Builder(requireContext())
            alertDialog.setMessage("Hello, ${mainArgs.name}")
                .setCancelable(true)
                .setTitle("Greetings")
                .create()
                .show()
        }
        return mBinding.root
    }

}

