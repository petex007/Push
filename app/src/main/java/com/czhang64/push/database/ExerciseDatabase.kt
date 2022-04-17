package com.czhang64.push.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Exercise::class], version=1, exportSchema=true)
abstract class ExerciseDatabase: RoomDatabase(){
    abstract fun exerciseDao():ExerciseDao
}
