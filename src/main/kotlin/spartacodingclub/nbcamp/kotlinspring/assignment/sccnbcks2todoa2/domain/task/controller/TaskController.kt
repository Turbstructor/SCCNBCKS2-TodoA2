package spartacodingclub.nbcamp.kotlinspring.assignment.sccnbcks2todoa2.domain.task.controller

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Slice
import org.springframework.data.domain.Sort
import org.springframework.data.web.PageableDefault
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import spartacodingclub.nbcamp.kotlinspring.assignment.sccnbcks2todoa2.domain.task.dto.request.CreateTaskRequest
import spartacodingclub.nbcamp.kotlinspring.assignment.sccnbcks2todoa2.domain.task.dto.request.ReadTaskRequest
import spartacodingclub.nbcamp.kotlinspring.assignment.sccnbcks2todoa2.domain.task.dto.request.UpdateTaskRequest
import spartacodingclub.nbcamp.kotlinspring.assignment.sccnbcks2todoa2.domain.task.dto.response.TaskResponse
import spartacodingclub.nbcamp.kotlinspring.assignment.sccnbcks2todoa2.domain.task.service.TaskService

@RestController
@RequestMapping("/api/v1/tasks")
class TaskController (

    private val taskService: TaskService
) {

    private val objectMapper = ObjectMapper()

    @PostMapping
    fun createTask(
        @RequestBody request: CreateTaskRequest
    ): ResponseEntity<TaskResponse> =
        ResponseEntity
            .status(HttpStatus.CREATED)
            .body(taskService.createTask(request))


    @GetMapping
    fun readAllTasks(
        @PageableDefault(size = 10, sort = ["id"], direction = Sort.Direction.ASC)
        pageSettings: Pageable,
        @RequestParam
        queries: Map<String, String?>
    ): ResponseEntity<Slice<TaskResponse>> =
        ResponseEntity
            .status(HttpStatus.OK)
            .body(taskService.readAllTasks(
                pageSettings,
                objectMapper.convertValue(queries, ReadTaskRequest::class.java)
            ))

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


    @PatchMapping("/{taskId}/completion")
    fun updateTaskCompletion(
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