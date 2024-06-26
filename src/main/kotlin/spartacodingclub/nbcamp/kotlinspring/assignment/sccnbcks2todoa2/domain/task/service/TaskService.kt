package spartacodingclub.nbcamp.kotlinspring.assignment.sccnbcks2todoa2.domain.task.service

import org.springframework.stereotype.Service
import spartacodingclub.nbcamp.kotlinspring.assignment.sccnbcks2todoa2.domain.task.dto.request.CreateTaskRequest
import spartacodingclub.nbcamp.kotlinspring.assignment.sccnbcks2todoa2.domain.task.dto.request.UpdateTaskRequest
import spartacodingclub.nbcamp.kotlinspring.assignment.sccnbcks2todoa2.domain.task.dto.response.TaskResponse
import spartacodingclub.nbcamp.kotlinspring.assignment.sccnbcks2todoa2.domain.task.repository.TaskRepository

@Service
class TaskService (

    private val taskRepository: TaskRepository
) {

    fun createTask(
        request: CreateTaskRequest
    ): TaskResponse = TODO()


    fun readAllTasks(): List<TaskResponse> = TODO()

    fun readTask(
        taskId: Long
    ): TaskResponse = TODO()


    fun updateTaskData(
        taskId: Long,
        request: UpdateTaskRequest
    ): TaskResponse = TODO()

    fun updateTaskCompletion(
        taskId: Long
    ): TaskResponse = TODO()


    fun deleteTask(
        taskId: Long
    ) {
        TODO()
    }
}