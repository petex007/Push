package com.czhang64.keep.ui

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.czhang64.keep.R
import com.czhang64.keep.databinding.MainFragmentBinding
import com.czhang64.keep.ui.MainViewModel

class MainFragment : Fragment() {
    private val sharedViewModel: MainViewModel by activityViewModels()
    private var binding: MainFragmentBinding?=null

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        val bindingMain = MainFragmentBinding.inflate(inflater, container, false)
        binding = bindingMain
        binding?.apply{
            mainAddBt.setOnClickListener {
                findNavController().navigate(R.id.action_mainFragment_to_addFragment)
            }
            mainDelBt.setOnClickListener {
                TODO()
            }
        }
        return bindingMain.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        // TODO: Use the ViewModel
    }

}