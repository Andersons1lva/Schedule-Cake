package com.anderson.schedulecake.auth

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.anderson.schedulecake.R
import com.anderson.schedulecake.databinding.FragmentHomeBinding
import com.anderson.schedulecake.databinding.FragmentRecoveryAccountBinding

class RecoveryAccountFragment : Fragment() {

    private var _binding: FragmentRecoveryAccountBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentRecoveryAccountBinding.inflate(inflater,container, false)
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}