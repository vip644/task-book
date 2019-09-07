package com.vipin.taskbook.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.vipin.taskbook.model.Tasks

/**
 * Created by vipin.c on 07/09/2019
 */

@Dao
interface TaskDao {

    @Insert
    fun insert(task: Tasks)

    @Query("SELECT * FROM tasks")
    fun getTaskList() : LiveData<List<Tasks>>

    @Query("SELECT * FROM tasks WHERE id = :taskId")
    fun getTaskDetail(taskId: Int) : LiveData<Tasks>

    @Delete
    fun deleteTask(task: Tasks)
}