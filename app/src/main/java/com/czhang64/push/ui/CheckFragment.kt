package com.czhang64.push.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.czhang64.push.R
import com.czhang64.push.databinding.FragmentCheckBinding

class CheckFragment : Fragment() {
    private val sharedViewModel: MainViewModel by activityViewModels()
    private var binding: FragmentCheckBinding?=null



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val fragmentCheckBinding = FragmentCheckBinding.inflate(inflater,container,false)
        binding = fragmentCheckBinding
        binding?.apply {
            when(sharedViewModel.toDelete.type){
                0->imageView3.setImageResource(R.drawable.type_chest)
                1->imageView3.setImageResource(R.drawable.type_cardio)
                2->imageView3.setImageResource(R.drawable.type_leg)
                3->imageView3.setImageResource(R.drawable.type_arm)
                else->imageView3.setImageResource(R.drawable.type_back)
            }
            checkNameTxt.text = sharedViewModel.toDelete.name.toString()
            checkTimeTxt.text = sharedViewModel.toDelete.time.toString()
            checkWeightTxt.text = sharedViewModel.toDelete.weight.toString()
            checkCommentTxt.text = sharedViewModel.toDelete.comment.toString()
            backBt.setOnClickListener {
                findNavController().navigate(R.id.action_checkFragment_to_mainFragment)
            }
        }
        return fragmentCheckBinding.root
    }
}