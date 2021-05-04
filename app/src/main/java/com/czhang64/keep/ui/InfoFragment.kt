package com.czhang64.keep.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.czhang64.keep.R
import com.czhang64.keep.databinding.FragmentInfoBinding


class InfoFragment : Fragment() {
    private val viewModel: MainViewModel by activityViewModels()
    private var binding: FragmentInfoBinding?=null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val fragmentInfoBinding = FragmentInfoBinding.inflate(inflater,container,false)
        binding = fragmentInfoBinding
        binding?.apply{
            infoBackBt.setOnClickListener {
                findNavController().navigate(R.id.action_infoFragment_to_mainFragment)
            }
        }
        return fragmentInfoBinding.root
    }

}