package com.vipin.taskbook.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.vipin.taskbook.R
import com.vipin.taskbook.model.Tasks
import kotlinx.android.synthetic.main.item_task_view.view.*

/**
 * Created by vipin.c on 07/09/2019
 */
class TaskListAdapter(private val clickListener: (Tasks) -> Unit) : RecyclerView.Adapter<TaskListAdapter.ViewHolder>() {

    private val taskList: MutableList<Tasks> = ArrayList()

    fun addTask(tasks: Tasks){
        if (!taskList.contains(tasks)) {
            taskList.add(tasks)
            notifyDataSetChanged()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_task_view, parent, false))
    }

    override fun getItemCount(): Int {
        return taskList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(taskList[position], clickListener)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(task: Tasks, clickListener: (Tasks) -> Unit){
            itemView.task_title.text = task.title
            itemView.setOnClickListener { clickListener(task) }
        }
    }
}