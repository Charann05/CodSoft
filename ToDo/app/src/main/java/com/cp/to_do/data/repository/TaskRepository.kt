package com.cp.to_do.data.repository

import androidx.lifecycle.LiveData
import com.cp.to_do.data.local.TaskDao
import com.cp.to_do.data.model.Task
import kotlinx.coroutines.flow.Flow

class TaskRepository(private val taskDao: TaskDao) {

    val allTasks: Flow<List<Task>> = taskDao.getAllTasks()

    suspend fun insert(task: Task) {
        taskDao.insertTask(task)
    }

    suspend fun update(task: Task) {
        taskDao.updateTask(task)
    }

    suspend fun delete(task: Task) {
        taskDao.deleteTask(task)
    }

    fun getTaskById(id: Int): LiveData<Task> = taskDao.getTaskById(id)

}
