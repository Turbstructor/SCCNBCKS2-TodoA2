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
import spartacodingclub.nbcamp.kotlinspring.assignment.sccnbcks2todoa2.global.exception.type.NonExistentEntityException

@Service
class TaskServiceImpl (

    private val taskRepository: TaskRepository
) : TaskService {

    override fun createTask(
        request: CreateTaskRequest
    ): TaskResponse =
        TaskResponse.from(
            taskRepository.save(
                request.toEntity()
            )
        )


    override fun readAllTasks(
    ): List<TaskResponse> =
        taskRepository.findAll().map {
            TaskResponse.from(it)
        }

    override fun readTask(
        taskId: Long
    ): TaskResponse =
        TaskResponse.from(
            taskRepository.findByIdOrNull(taskId)
                ?: throw NonExistentEntityException("Task not found with id: $taskId")
        )


    override fun updateTaskData(
        taskId: Long,
        request: UpdateTaskRequest
    ): TaskResponse {

        val targetTask = taskRepository.findByIdOrNull(taskId)
            ?: throw NonExistentEntityException("Task not found with id: $taskId")

        request.updateEntity(targetTask)

        return TaskResponse.from(
            taskRepository.save(targetTask)
        )
    }

    override fun updateTaskCompletion(
        taskId: Long
    ): TaskResponse {

        val targetTask = taskRepository.findByIdOrNull(taskId)
            ?: throw NonExistentEntityException("Task not found with id: $taskId")

        targetTask.toggleTaskCompletion()

        return TaskResponse.from(
            taskRepository.save(targetTask)
        )
    }


    override fun deleteTask(
        taskId: Long
    ) =
        if (taskRepository.existsById(taskId)) taskRepository.deleteById(taskId)
        else throw NonExistentEntityException("Task not found with id: $taskId")

}