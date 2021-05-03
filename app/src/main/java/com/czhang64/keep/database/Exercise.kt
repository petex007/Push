package com.czhang64.keep.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName="exercise_table")
data class Exercise(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "exercise_id")
    var id: Long = 0L,

    @ColumnInfo(name="exercise_name")
    var name: String = "",

    @ColumnInfo(name="exercise_type")
    var type: String = "",

    @ColumnInfo(name="exercise_time")
    var time: String = "",

    @ColumnInfo(name="exercise_weight")
    var weight: String = "",

    @ColumnInfo(name="exercise_comment")
    var comment: String= "",
)