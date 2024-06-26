package spartacodingclub.nbcamp.kotlinspring.assignment.sccnbcks2todoa2.domain.task.controller

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import spartacodingclub.nbcamp.kotlinspring.assignment.sccnbcks2todoa2.domain.task.dto.request.CreateTaskRequest
import spartacodingclub.nbcamp.kotlinspring.assignment.sccnbcks2todoa2.domain.task.dto.request.UpdateTaskRequest
import spartacodingclub.nbcamp.kotlinspring.assignment.sccnbcks2todoa2.domain.task.dto.response.TaskResponse
import spartacodingclub.nbcamp.kotlinspring.assignment.sccnbcks2todoa2.domain.task.service.TaskService

@RestController
@RequestMapping("/api/v1/tasks")
class TaskController (

    private val taskService: TaskService
) {

    @PostMapping
    fun createTask(
        @RequestBody request: CreateTaskRequest
    ): ResponseEntity<TaskResponse> = TODO()


    @GetMapping
    fun readAllTasks(): ResponseEntity<List<TaskResponse>> = TODO()

    @GetMapping("/{taskId}")
    fun readTask(
        @PathVariable taskId: Long
    ): ResponseEntity<TaskResponse> = TODO()


    @PutMapping("/{taskId}")
    fun updateTaskData(
        @PathVariable taskId: Long,
        @RequestBody request: UpdateTaskRequest
    ): ResponseEntity<TaskResponse> = TODO()


    @PatchMapping("/{taskId}")
    fun updateTaskCompletion(
        @PathVariable taskId: Long
    ): ResponseEntity<TaskResponse> = TODO()


    @DeleteMapping("/{taskId}")
    fun deleteTask(
        @PathVariable taskId: Long
    ): ResponseEntity<Unit> = TODO()
}