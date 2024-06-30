package spartacodingclub.nbcamp.kotlinspring.assignment.sccnbcks2todoa2.domain.task.service

import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Slice
import spartacodingclub.nbcamp.kotlinspring.assignment.sccnbcks2todoa2.domain.task.dto.request.CreateTaskRequest
import spartacodingclub.nbcamp.kotlinspring.assignment.sccnbcks2todoa2.domain.task.dto.request.UpdateTaskRequest
import spartacodingclub.nbcamp.kotlinspring.assignment.sccnbcks2todoa2.domain.task.dto.response.TaskResponse

interface TaskService {

    fun createTask(request: CreateTaskRequest) : TaskResponse
    fun readAllTasks(pageSettings: Pageable) : Slice<TaskResponse>
    fun readTask(taskId: Long) : TaskResponse
    fun updateTaskData(taskId: Long, request: UpdateTaskRequest) : TaskResponse
    fun updateTaskCompletion(taskId: Long) : TaskResponse
    fun deleteTask(taskId: Long)
}