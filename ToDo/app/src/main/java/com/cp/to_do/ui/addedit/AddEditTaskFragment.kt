package com.cp.to_do.ui.addedit

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.cp.to_do.R
import com.cp.to_do.data.local.TaskDatabase
import com.cp.to_do.data.model.Task
import com.cp.to_do.data.repository.TaskRepository
import com.cp.to_do.viewmodel.TaskViewModel
import com.cp.to_do.viewmodel.TaskViewModelFactory
import java.util.Calendar

class AddEditTaskFragment : Fragment() {

    private lateinit var taskViewModel: TaskViewModel
    private var selectedDueDate: Long? = null


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
        val prioritySpinner = view.findViewById<Spinner>(R.id.prioritySpinner)
        val dueDateBtn = view.findViewById<Button>(R.id.btnDueDate)

        val taskId = arguments?.getInt("taskId", -1) ?: -1
        if (taskId != -1) {
            taskViewModel.getTaskById(taskId).observe(viewLifecycleOwner) { task ->
                task?.let {
                    titleInput.setText(it.title)
                    descriptionInput.setText(it.description)

                    prioritySpinner.setSelection(it.priority)

                    selectedDueDate = it.dueDate
                    if (it.dueDate != null) {
                        val cal = Calendar.getInstance()
                        cal.timeInMillis = it.dueDate!!
                        dueDateBtn.text =
                            "Due: ${cal.get(Calendar.DAY_OF_MONTH)}/${cal.get(Calendar.MONTH) + 1}/${cal.get(Calendar.YEAR)}"
                    }
                }
            }
        }



        saveBtn.setOnClickListener {
            val title = titleInput.text.toString()
            val description = descriptionInput.text.toString()
            val priority = prioritySpinner.selectedItemPosition
            val dueDate = selectedDueDate

            if (title.isEmpty()) return@setOnClickListener

            if (taskId == -1) {
                taskViewModel.addTask(
                    Task(title = title, description = description, priority = priority, dueDate = dueDate, isCompleted = false)
                )

            } else {
                taskViewModel.updateTask(
                    Task(id = taskId, title = title, description = description, priority = priority, dueDate = dueDate, isCompleted = false)
                )

            }

            findNavController().navigateUp()
        }

        val priorities = arrayOf("Low", "Medium", "High")

        prioritySpinner.adapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_spinner_dropdown_item,
            priorities
        )

        dueDateBtn.setOnClickListener {
            val calendar = Calendar.getInstance()

            DatePickerDialog(
                requireContext(),
                { _, year, month, day ->
                    calendar.set(year, month, day)
                    selectedDueDate = calendar.timeInMillis
                    dueDateBtn.text = "Due: $day/${month + 1}/$year"
                },
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
            ).show()
        }


    }
}
