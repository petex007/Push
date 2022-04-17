package com.czhang64.push.ui

import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.*
import android.widget.RadioButton
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.czhang64.push.R
import com.czhang64.push.databinding.FragmentSettingsBinding


class SettingsFragment : Fragment() {
    private var binding: FragmentSettingsBinding? = null
    private val sharedViewModel: MainViewModel by activityViewModels()
    private var select = 0
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)

        val fragmentSettingsBinding = FragmentSettingsBinding.inflate(inflater, container, false)
        binding = fragmentSettingsBinding
        binding?.apply{
//            setApplyBt.setOnClickListener {
//                sharedViewModel.set = select
//                findNavController().navigate(R.id.action_settingsFragment_to_mainFragment)
//            }
            setNoti.setOnClickListener {

                val msg = "What type of media?"
                val builder = AlertDialog.Builder(context)
                builder.setTitle(msg)
                builder.setItems(
                    arrayOf<CharSequence>("Audio", "Video")
                ) { _, which -> // The 'which' argument contains the index position
                    // of the selected item
                    when (which) {
                        0 -> openFileChooser()
                        1 -> Toast.makeText(context, "video", Toast.LENGTH_SHORT).show()
                    }
                }
                builder.create().show()
            }


            radioGroup.setOnCheckedChangeListener{group, id->
                val rad:RadioButton = group.findViewById(id)
                select = group.indexOfChild(rad)

            }
        }
        return fragmentSettingsBinding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        menu.clear()
    }


    val REQUEST_CODE = 1

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            if(data == null){
                return
            }

            Toast.makeText(context, data.data?.path, Toast.LENGTH_SHORT).show()
        }
    }

    private fun openFileChooser(){
        val intent = Intent(Intent.ACTION_GET_CONTENT)
        intent.type = "*/*"
        startActivityForResult(intent, REQUEST_CODE)
    }
}