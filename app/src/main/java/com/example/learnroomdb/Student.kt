package com.example.learnroomdb

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "student_table")
data class Student(
    @PrimaryKey(autoGenerate = true)val id:Int?,
    @ColumnInfo(name = "name")val name:String?,
    @ColumnInfo(name = "marks")val marks:Int?,
    @ColumnInfo(name = "roll_no")val rollNo:Int?,
)
