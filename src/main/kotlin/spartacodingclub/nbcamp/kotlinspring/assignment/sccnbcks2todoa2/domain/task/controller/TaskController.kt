package spartacodingclub.nbcamp.kotlinspring.assignment.sccnbcks2todoa2.domain.task.controller

import org.springframework.http.HttpStatus
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
    ): ResponseEntity<TaskResponse> =
        ResponseEntity
            .status(HttpStatus.CREATED)
            .body(taskService.createTask(request))


    @GetMapping
    fun readAllTasks(
    ): ResponseEntity<List<TaskResponse>> =
        ResponseEntity
            .status(HttpStatus.OK)
            .body(taskService.readAllTasks())

    @GetMapping("/{taskId}")
    fun readTask(
        @PathVariable taskId: Long
    ): ResponseEntity<TaskResponse> =
        ResponseEntity
            .status(HttpStatus.OK)
            .body(taskService.readTask(taskId))


    @PutMapping("/{taskId}")
    fun updateTaskData(
        @PathVariable taskId: Long,
        @RequestBody request: UpdateTaskRequest
    ): ResponseEntity<TaskResponse> =
        ResponseEntity
            .status(HttpStatus.OK)
            .body(taskService.updateTaskData(taskId, request))


    @PatchMapping("/{taskId}/status")
    fun updateTaskStatus(
        @PathVariable taskId: Long
    ): ResponseEntity<TaskResponse> =
        ResponseEntity
            .status(HttpStatus.OK)
            .body(taskService.updateTaskCompletion(taskId))


    @DeleteMapping("/{taskId}")
    fun deleteTask(
        @PathVariable taskId: Long
    ): ResponseEntity<Unit> =
        ResponseEntity
            .status(HttpStatus.NO_CONTENT)
            .body(taskService.deleteTask(taskId))

}