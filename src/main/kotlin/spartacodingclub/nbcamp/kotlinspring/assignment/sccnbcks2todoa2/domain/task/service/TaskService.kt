package spartacodingclub.nbcamp.kotlinspring.assignment.sccnbcks2todoa2.domain.task.service

import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import spartacodingclub.nbcamp.kotlinspring.assignment.sccnbcks2todoa2.domain.task.dto.request.CreateTaskRequest
import spartacodingclub.nbcamp.kotlinspring.assignment.sccnbcks2todoa2.domain.task.dto.request.UpdateTaskRequest
import spartacodingclub.nbcamp.kotlinspring.assignment.sccnbcks2todoa2.domain.task.dto.request.toEntity
import spartacodingclub.nbcamp.kotlinspring.assignment.sccnbcks2todoa2.domain.task.dto.request.updateEntity
import spartacodingclub.nbcamp.kotlinspring.assignment.sccnbcks2todoa2.domain.task.dto.response.TaskResponse
import spartacodingclub.nbcamp.kotlinspring.assignment.sccnbcks2todoa2.domain.task.dto.response.from
import spartacodingclub.nbcamp.kotlinspring.assignment.sccnbcks2todoa2.domain.task.repository.TaskRepository

@Service
class TaskService (

    private val taskRepository: TaskRepository
) {

    fun createTask(
        request: CreateTaskRequest
    ): TaskResponse =
        TaskResponse.from(
            taskRepository.save(
                request.toEntity()
            )
        )


    fun readAllTasks(
    ): List<TaskResponse> =
        taskRepository.findAll().map {
            TaskResponse.from(it)
        }

    fun readTask(
        taskId: Long
    ): TaskResponse =
        TaskResponse.from(
            taskRepository.findByIdOrNull(taskId)
                ?: throw RuntimeException("Task not found with id: $taskId")
        )


    fun updateTaskData(
        taskId: Long,
        request: UpdateTaskRequest
    ): TaskResponse {

        val targetTask = taskRepository.findByIdOrNull(taskId)
            ?: throw RuntimeException("Task not found with id: $taskId")

        request.updateEntity(targetTask)

        return TaskResponse.from(
            taskRepository.save(targetTask)
        )
    }

    fun updateTaskCompletion(
        taskId: Long
    ): TaskResponse {

        val targetTask = taskRepository.findByIdOrNull(taskId)
            ?: throw RuntimeException("Task not found with id: $taskId")

        targetTask.toggleTaskCompletion()

        return TaskResponse.from(
            taskRepository.save(targetTask)
        )
    }


    fun deleteTask(
        taskId: Long
    ) =
        if (taskRepository.existsById(taskId)) taskRepository.deleteById(taskId)
        else throw RuntimeException("Task not found with id: $taskId")

}