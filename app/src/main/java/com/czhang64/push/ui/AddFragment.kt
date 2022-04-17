package com.czhang64.push.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.fragment.app.activityViewModels
import com.czhang64.push.R
import com.czhang64.push.database.Exercise
import com.czhang64.push.databinding.FragmentAddBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class AddFragment : BottomSheetDialogFragment(), AdapterView.OnItemSelectedListener {
    private val sharedViewModel: MainViewModel by activityViewModels()
    private var binding: FragmentAddBinding?=null
    private var newExercise = Exercise()
    private var restDay = Exercise()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val fragmentAddBinding = FragmentAddBinding.inflate(inflater, container, false)
        binding = fragmentAddBinding
        binding?.apply{
            typeSpinner.onItemSelectedListener = this@AddFragment

            addAddBt.setOnClickListener {
                var rest = editWeight.text.toString().toInt()
                var count = rest
                var loop = 1
                var bool = 0
                for (i in 0..6){
                    if (count==0){
                        with(restDay){
                            type = 5
                            name = "REST DAY"
                            time = "REST DAY"
                            weight = "REST DAY"
                            comment = "REST DAY"
                        }
                        sharedViewModel.insert(restDay)

                        count = rest
                    }
                    else{
                        with(newExercise){
                            name = editName.text.toString()
                            time = editTime.text.toString()
                            weight = editWeight.text.toString()
                            comment = editComment.text.toString()
                        }
                        sharedViewModel.insert(newExercise)
                        count = count -1

                    }

                }
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