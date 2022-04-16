package com.czhang64.keep.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.navGraphViewModels
import com.czhang64.keep.R
import com.czhang64.keep.databinding.FragmentAddBinding
import com.czhang64.keep.databinding.FragmentSettingsBinding

class SettingsFragment : Fragment() {
    private var binding: FragmentSettingsBinding? = null
    private val sharedViewModel: MainViewModel by activityViewModels()
    private var select = 0
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val fragmentSettingsBinding = FragmentSettingsBinding.inflate(inflater, container, false)
        binding = fragmentSettingsBinding
        binding?.apply{
            setApplyBt.setOnClickListener {
                sharedViewModel.set = select
                findNavController().navigate(R.id.action_settingsFragment_to_mainFragment)
            }
            radioGroup.setOnCheckedChangeListener{group, id->
                val rad:RadioButton = group.findViewById(id)
                select = group.indexOfChild(rad)

            }
        }
        return fragmentSettingsBinding.root
    }

}