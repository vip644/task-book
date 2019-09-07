package com.vipin.taskbook.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.vipin.taskbook.R
import com.vipin.taskbook.adapter.TaskListAdapter
import com.vipin.taskbook.db.TaskDao
import com.vipin.taskbook.db.TaskDatabase
import com.vipin.taskbook.model.Tasks
import kotlinx.android.synthetic.main.content_main.*
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {

    private lateinit var mTaskListAdapter: TaskListAdapter
    private lateinit var database: TaskDatabase
    private lateinit var taskDao: TaskDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        database = TaskDatabase.getInstance(this)
        taskDao = database.daoTask()

        addTaskBtn.setOnClickListener {
            addTaskInList()
        }

        mTaskListAdapter = TaskListAdapter {
            val taskId = it.id
            startActivity(TaskDetailActivity.launchIntent(this, taskId))
        }
        taskRecyclerView.layoutManager = LinearLayoutManager(this)
        taskRecyclerView.adapter = mTaskListAdapter

        taskDao.getTaskList().observe(this, Observer<List<Tasks>> {
            mTaskListAdapter
        })
    }

    private fun addTaskInList() {

        val title = typeInputTask.text.toString()

        if (title.isBlank()) {
            Toast.makeText(this, "Task title should not be empty!!", Toast.LENGTH_SHORT).show()
            return
        }
        val task = Tasks(title = title)
        thread {
            taskDao.insert(task)
            typeInputTask.text?.clear()
        }

    }
}
