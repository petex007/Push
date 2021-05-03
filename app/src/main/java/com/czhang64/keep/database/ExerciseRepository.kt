package com.czhang64.keep.database

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.Room
import java.util.concurrent.Executors

class ExerciseRepository private constructor(context: Context) {

    private val database: ExerciseDatabase = Room.databaseBuilder(
        context.applicationContext,
        ExerciseDatabase::class.java,
        "exercise_database"
    ).build()

    private val friendDao = database.exerciseDao()
    private val executor = Executors.newSingleThreadExecutor()

    fun getAllFriends(): LiveData<List<Exercise>> = friendDao.getAllFriends()

    fun insert(friend: Exercise) {
        executor.execute {
            friendDao.insert(friend)
        }
    }

    fun deleteFriend(friend: Exercise) {
        executor.execute {
            friendDao.deleteExercise(friend)
        }
    }

    companion object {
        private var INSTANCE: ExerciseRepository? = null

        fun initialize(context: Context) {
            if (INSTANCE == null) {
                INSTANCE = ExerciseRepository(context)
            }
        }

        fun get(): ExerciseRepository {
            return INSTANCE
                ?: throw java.lang.IllegalStateException("ExerciseRepository must be initialized.")
        }
    }
}
