package com.czhang64.push.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ExerciseDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(exercise: Exercise)

    @Delete
    fun deleteExercise(exercise: Exercise)

    @Query("SELECT * FROM exercise_table ORDER BY exercise_name DESC")
    fun getAllExercises(): LiveData<List<Exercise>>
}