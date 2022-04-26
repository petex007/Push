package com.czhang64.push.ui

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.czhang64.push.databinding.FragmentHistoryBinding


class HistoryFragment : Fragment() {
    private var binding: FragmentHistoryBinding? = null
    private val sharedViewModel: MainViewModel by activityViewModels()
    private var select = 0
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)

        val fragmentHistoryBinding = FragmentHistoryBinding.inflate(inflater, container, false)
        binding = fragmentHistoryBinding
        binding?.apply {

        }
        return fragmentHistoryBinding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        menu.clear()
    }
}