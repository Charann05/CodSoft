package com.cp.to_do.ui.tasklist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.cp.to_do.R
import com.cp.to_do.data.model.Task
import java.util.Calendar

class TaskAdapter(
    private val onItemClick: (Task) -> Unit,
    private val onCheckBoxClick: (Task, Boolean) -> Unit,
    private val onDeleteClick: (Task) -> Unit
) : RecyclerView.Adapter<TaskAdapter.TaskViewHolder>() {


    private var tasks = emptyList<Task>()

    inner class TaskViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.findViewById(R.id.textTitle)
        val description: TextView = itemView.findViewById(R.id.textDescription)
        val checkBox: CheckBox = itemView.findViewById(R.id.checkCompleted)

        val deleteBtn: ImageButton = itemView.findViewById(R.id.btnDelete)
        val priority: TextView = itemView.findViewById(R.id.textPriority)
        val dueDate: TextView = itemView.findViewById(R.id.textDueDate)


        init {
            itemView.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    onItemClick(tasks[position])
                }
            }

            checkBox.setOnCheckedChangeListener { _, isChecked ->
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    onCheckBoxClick(tasks[position], isChecked)
                }
            }

            deleteBtn.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    onDeleteClick(tasks[position])
                }
            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_task, parent, false)
        return TaskViewHolder(view)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val task = tasks[position]
        holder.title.text = task.title
        holder.description.text = task.description
        holder.checkBox.isChecked = task.isCompleted
        val priorities = arrayOf("Low", "Medium", "High")

        holder.priority.text = priorities[task.priority]

        task.dueDate?.let {
            val cal = Calendar.getInstance()
            cal.timeInMillis = it
            holder.dueDate.text = "Due: ${cal.get(Calendar.DAY_OF_MONTH)}/${cal.get(Calendar.MONTH)+1}/${cal.get(Calendar.YEAR)}"
        } ?: run {
            holder.dueDate.text = ""
        }

    }

    override fun getItemCount(): Int = tasks.size

    fun submitList(list: List<Task>) {
        tasks = list
        notifyDataSetChanged()
    }
}
