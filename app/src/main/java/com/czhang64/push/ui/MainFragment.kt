package com.czhang64.keep.ui

import android.app.AlertDialog
import android.content.Context
import android.graphics.Color
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.czhang64.keep.R
import com.czhang64.keep.database.Exercise
import com.czhang64.keep.databinding.MainFragmentBinding
import com.czhang64.keep.ui.MainViewModel
import com.squareup.picasso.Picasso

class MainFragment : Fragment() {
    private val sharedViewModel: MainViewModel by activityViewModels()
    private var binding: MainFragmentBinding?=null
    private val exerciseAdapter = ExerciseAdapter()
    var pos = -1

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        val bindingMain = MainFragmentBinding.inflate(inflater, container, false)
        binding = bindingMain
        binding?.apply{
            mainAddBt.setOnClickListener {
                findNavController().navigate(MainFragmentDirections.actionMainFragmentToAddFragment())
            }
            mainDelBt.setOnClickListener {
                itemDeletedAlert(sharedViewModel.toDelete)
            }
            checkbt.setOnClickListener {
                findNavController().navigate(R.id.action_mainFragment_to_checkFragment)
            }
            recyclerView.run{
                layoutManager = LinearLayoutManager(context)
                adapter = exerciseAdapter
            }
        }
        return bindingMain.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        sharedViewModel.exercises.observe(viewLifecycleOwner,{
            exerciseAdapter.updateWords(it)
        })
    }
    fun itemDeletedAlert(exercise: Exercise){
        val msg = resources.getString(R.string.word_deleted_alert,exercise.name)
        val builder = AlertDialog.Builder(context)
        with(builder){
            setTitle("Alert")
            setMessage(msg)
            setPositiveButton("OK",null)
            setPositiveButton(R.string.yes){_,_->
                sharedViewModel.deleteExercise(exercise = sharedViewModel.toDelete)
            }
            setNegativeButton(R.string.no){_,_,->

            }
            show()
        }
    }
    private inner class ExerciseViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private lateinit var exercise: Exercise
        private val wordTextView: TextView = itemView.findViewById(R.id.item_textView1)
        private val wordImageView: ImageView = itemView.findViewById(R.id.item_photo_imageView)
        private val picasso = Picasso.get()
        fun bind(exercise: Exercise) {
            this.exercise = exercise
            when(sharedViewModel.set){
                0->wordTextView.text = "Name: ${exercise.name} \n Date: ${exercise.time}"
                1->wordTextView.text = "Name: ${exercise.name} \n Comment: ${exercise.comment}"
                else->wordTextView.text = "Date: ${exercise.time} \n Comment: ${exercise.comment}"
            }
            when(exercise.type){
                0->wordImageView.setImageResource(R.drawable.type_chest)
                1->wordImageView.setImageResource(R.drawable.type_cardio)
                2 -> wordImageView.setImageResource(R.drawable.type_leg)
                3->wordImageView.setImageResource(R.drawable.type_arm)
                4->wordImageView.setImageResource(R.drawable.type_back)
            }
        }
    }

    private inner class ExerciseAdapter : RecyclerView.Adapter<ExerciseViewHolder>() {
        var words: List<Exercise> = emptyList()

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExerciseViewHolder {
            val view = layoutInflater.inflate(R.layout.recycler_item, parent, false)
            return ExerciseViewHolder(view)
        }

        override fun onBindViewHolder(holder: ExerciseViewHolder, position: Int) {
            if (pos == position) {
                holder.itemView.setBackgroundColor(Color.parseColor("#FF000000"))
            }
            else {
                holder.itemView.setBackgroundColor(Color.parseColor("#FF7A7A7A"))
            }
            holder.bind(words[position])
            holder.itemView.setOnClickListener {
                pos = position
                sharedViewModel.toDelete = words[position]
                notifyDataSetChanged()
            }

        }

        override fun getItemCount() = words.size

        fun updateWords(newWords: List<Exercise>) {
            this.words = newWords
            notifyDataSetChanged()
        }

        fun getWordAtPosition(position: Int): Exercise {
            return words[position]
        }
    }
}
fun Context.toast(message: String, duration: Int = Toast.LENGTH_SHORT){
    Toast.makeText(this, message , duration).show()
}