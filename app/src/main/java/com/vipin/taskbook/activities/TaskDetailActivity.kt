package com.vipin.taskbook.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.vipin.taskbook.R
import com.vipin.taskbook.db.TaskDao
import com.vipin.taskbook.db.TaskDatabase
import com.vipin.taskbook.model.Tasks
import com.vipin.taskbook.utils.Constants.Companion.TASK_ID
import kotlinx.android.synthetic.main.content_task_detail.*
import java.lang.IllegalArgumentException
import kotlin.concurrent.thread

/**
 * Created by vipin.c on 07/09/2019
 */
class TaskDetailActivity: AppCompatActivity() {

    private lateinit var taskDao: TaskDao
    private lateinit var task: Tasks

    companion object{

        fun launchIntent(context: Context, taskId : Int) : Intent{
            val intent = Intent(context, TaskDetailActivity::class.java)
            intent.putExtra(TASK_ID, taskId)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task_detail)

        taskDao = TaskDatabase.getInstance(this).daoTask()
        val taskId = extractTaskId()

        taskDao.getTaskDetail(taskId).observe(this, Observer<Tasks>{

            if (it == null){
                finish()
                return@Observer
            }

            task_id.text = it.id.toString()
            task_title.text = it.title
            task = it
        })

    }

    private fun extractTaskId(): Int{
        if (!intent.hasExtra(TASK_ID)){
            throw IllegalArgumentException("$TASK_ID must be provided to start this Activity")
        }
        return intent.getIntExtra(TASK_ID, Int.MIN_VALUE)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        menuInflater.inflate(R.menu.menu_task_items, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        return when(item!!.itemId){
            R.id.btn_delete -> {
                thread { taskDao.deleteTask(task) }
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}