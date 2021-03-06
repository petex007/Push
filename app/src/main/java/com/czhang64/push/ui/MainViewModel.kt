package com.czhang64.push.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.czhang64.push.MainActivity.Companion.DEFAULT_WEBVIEW_URL
import com.czhang64.push.database.Exercise
import com.czhang64.push.database.ExerciseRepository


class MainViewModel(app: Application) : AndroidViewModel(app) {

    private val _url = MutableLiveData<String>().apply {
        value = DEFAULT_WEBVIEW_URL
    }
    val url: LiveData<String> = _url
    fun setUrl(url: String){
        _url.value=url
    }

    var set = 0
    init{
        ExerciseRepository.initialize(app)
    }
    private val exerciseRepository = ExerciseRepository.get()
    val exercises = exerciseRepository.getAllExercises()
    var toDelete = Exercise()

    fun insert(exercise: Exercise){
        exerciseRepository.insert(exercise)
    }
    fun deleteExercise(exercise: Exercise){
        exerciseRepository.deleteExercise(exercise)
    }
}