package com.vipin.taskbook.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.vipin.taskbook.R
import com.vipin.taskbook.adapter.TaskListAdapter
import com.vipin.taskbook.model.Tasks
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var mTaskListAdapter: TaskListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        addTaskBtn.setOnClickListener{
            addTaskInList()
        }

        mTaskListAdapter = TaskListAdapter()
        taskRecyclerView.layoutManager = LinearLayoutManager(this)
        taskRecyclerView.adapter = mTaskListAdapter
    }

    private fun addTaskInList() {

        val title = typeTask.text.toString()

        if (title.isBlank()){
            Toast.makeText(this, "Task title should not be empty!!", Toast.LENGTH_SHORT).show()
            return
        }

        val task = Tasks(title = title)
        mTaskListAdapter.addTask(task)
    }
}
