package spartacodingclub.nbcamp.kotlinspring.assignment.sccnbcks2todoa2.domain.task.dto.response

import spartacodingclub.nbcamp.kotlinspring.assignment.sccnbcks2todoa2.domain.task.entity.Task
import java.time.LocalDateTime
import java.time.ZonedDateTime

data class TaskResponse (

    val id: Long,

    val title: String,
    val description: String,
    val isDone: Boolean,
    val priority: Byte,
    val startsAt: ZonedDateTime?,
    val finishesAt: ZonedDateTime?,

    val createdAt: ZonedDateTime,
    val updatedAt: ZonedDateTime
) {

    companion object
}

fun TaskResponse.Companion.from(task: Task) =
    TaskResponse (
        id = task.id!!,
        title = task.title,
        description = task.description,
        isDone = task.isDone,
        priority = task.priority,
        startsAt = task.startsAt,
        finishesAt = task.finishesAt,
        createdAt = task.time.createdAt,
        updatedAt = task.time.updatedAt
    )