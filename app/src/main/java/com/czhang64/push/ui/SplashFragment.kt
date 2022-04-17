package com.czhang64.push.ui

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.czhang64.push.R
import com.czhang64.push.databinding.FragmentSplashBinding

class SplashFragment : Fragment() {
    private val sharedViewModel: MainViewModel by activityViewModels()
    private var binding: FragmentSplashBinding?=null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)

        val SplashBinding = FragmentSplashBinding.inflate(inflater, container, false)
        binding = SplashBinding
        binding?.apply {
            button.setOnClickListener {
                findNavController().navigate(R.id.action_splashFragment_to_mainFragment)
            }
        }
        return SplashBinding.root
    }
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        menu.clear()
    }
}