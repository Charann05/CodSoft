package com.cp.to_do.ui.addedit

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.cp.to_do.R
import com.cp.to_do.data.local.TaskDatabase
import com.cp.to_do.data.model.Task
import com.cp.to_do.data.repository.TaskRepository
import com.cp.to_do.viewmodel.TaskViewModel
import com.cp.to_do.viewmodel.TaskViewModelFactory

class AddEditTaskFragment : Fragment() {

    private lateinit var taskViewModel: TaskViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_add_edit_task, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val app = requireActivity().application
        val dao = TaskDatabase.getDatabase(app).taskDao()
        val repository = TaskRepository(dao)
        val factory = TaskViewModelFactory(repository)

        taskViewModel = ViewModelProvider(requireActivity(), factory)[TaskViewModel::class.java]

        val titleInput = view.findViewById<EditText>(R.id.editTextTitle)
        val descriptionInput = view.findViewById<EditText>(R.id.editTextDescription)

        val saveBtn = view.findViewById<Button>(R.id.btnSave)

        val taskId = arguments?.getInt("taskId", -1) ?: -1
        if (taskId != -1) {
            taskViewModel.getTaskById(taskId).observe(viewLifecycleOwner) { task ->
                task?.let {
                    titleInput.setText(it.title)
                    descriptionInput.setText(it.description)
                }
            }
        }


        saveBtn.setOnClickListener {
            val title = titleInput.text.toString()
            val description = descriptionInput.text.toString()

            if (title.isEmpty()) return@setOnClickListener

            if (taskId == -1) {
                taskViewModel.addTask(
                    Task(title = title, description = description, isCompleted = false)
                )

            } else {
                taskViewModel.updateTask(
                    Task(id = taskId, title = title, description = description, isCompleted = false)
                )

            }

            findNavController().navigateUp()
        }
    }
}
