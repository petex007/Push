package com.czhang64.keep.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.czhang64.keep.R
import com.czhang64.keep.databinding.FragmentSplashBinding

class SplashFragment : Fragment() {
    private val sharedViewModel: MainViewModel by activityViewModels()
    private var binding: FragmentSplashBinding?=null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val SplashBinding = FragmentSplashBinding.inflate(inflater, container, false)
        binding = SplashBinding
        binding?.apply {
            button.setOnClickListener {
                findNavController().navigate(R.id.action_splashFragment_to_mainFragment)
            }
        }
        return SplashBinding.root
    }

}