package com.czhang64.keep.ui

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import androidx.navigation.navGraphViewModels
import com.czhang64.keep.R
import com.czhang64.keep.databinding.FragmentWebViewBinding

class WebViewFragment : Fragment() {

    private var binding: FragmentWebViewBinding? = null
    private val viewModel: MainViewModel by lazy {
        ViewModelProvider(this).get(MainViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val webviewFragmentBinding = FragmentWebViewBinding.inflate(inflater, container, false)
        binding = webviewFragmentBinding
        binding?.apply{
            webBackBt.setOnClickListener {
                findNavController().navigate(R.id.action_webViewFragment_to_mainFragment)
            }
        }
        return webviewFragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.apply {
            webView.webViewClient = WebViewClient()
            viewModel.url.observe(viewLifecycleOwner, {
                goBar.urlEditText.setText(it)
                loadUrl(it)
            })

            goBar.urlEditText.setOnKeyListener(View.OnKeyListener { _, keyCode, event ->
                if (keyCode == KeyEvent.KEYCODE_ENTER && event.action == KeyEvent.ACTION_UP) {
                    viewModel.setUrl(goBar.urlEditText.text.toString())
                    return@OnKeyListener true
                }
                false
            })
            goBar.goButton.setOnClickListener {
                viewModel.setUrl(goBar.urlEditText.text.toString())
            }
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    private fun loadUrl(request: String) {
        Log.d(TAG, "url in loadUrl: $request")
        binding?.webView?.loadUrl(request)
    }

}