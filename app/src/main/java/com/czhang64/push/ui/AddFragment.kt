package com.czhang64.keep.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.activityViewModels
import com.czhang64.keep.R
import com.czhang64.keep.database.Exercise
import com.czhang64.keep.databinding.FragmentAddBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import java.lang.NullPointerException

class AddFragment : BottomSheetDialogFragment(), AdapterView.OnItemSelectedListener {
    private val sharedViewModel: MainViewModel by activityViewModels()
    private var binding: FragmentAddBinding?=null
    private var newExercise = Exercise()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val fragmentAddBinding = FragmentAddBinding.inflate(inflater, container, false)
        binding = fragmentAddBinding
        binding?.apply{
            typeSpinner.onItemSelectedListener = this@AddFragment

            addAddBt.setOnClickListener {
                with(newExercise){
                    name = editName.text.toString()
                    time = editTime.text.toString()
                    weight = editWeight.text.toString()
                    comment = editComment.text.toString()
                }
                sharedViewModel.insert(newExercise)
                dismiss()
            }
            addCancelBt.setOnClickListener {
                dismiss()
            }
        }
        return fragmentAddBinding.root
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        binding?.apply{
            when(parent){
                typeSpinner->{
                    newExercise.type = position
                    imageView2.setImageResource(when(newExercise.type){
                        0 -> R.drawable.type_chest
                        1 -> R.drawable.type_cardio
                        2 -> R.drawable.type_leg
                        3 -> R.drawable.type_arm
                        else -> R.drawable.type_back
                    })

                }
            }
        }
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
    }

    companion object{
        private const val YEAR_ZERO = 1984
    }

}