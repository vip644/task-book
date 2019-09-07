package com.vipin.taskbook.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.vipin.taskbook.model.Tasks

/**
 * Created by vipin.c on 07/09/2019
 */

//you can keep multiple entities in the database
@Database(entities = [Tasks::class], version = 1)
abstract class TaskDatabase : RoomDatabase() {

    abstract fun daoTask(): TaskDao

    companion object {

        @Volatile
        private var INSTANCE: TaskDatabase? = null

        fun getInstance(context: Context): TaskDatabase {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: buildDB(context).also { INSTANCE = it }
            }
        }

        private fun buildDB(context: Context): TaskDatabase {
            return Room.databaseBuilder(
                context.applicationContext,
                TaskDatabase::class.java,
                "task-database"
            ).build()
        }
    }


}