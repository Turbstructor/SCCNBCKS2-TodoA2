package spartacodingclub.nbcamp.kotlinspring.assignment.sccnbcks2todoa2.domain.task.dto.request

import spartacodingclub.nbcamp.kotlinspring.assignment.sccnbcks2todoa2.domain.task.entity.Task
import java.time.ZonedDateTime

data class CreateTaskRequest (

    val title: String,
    val description: String,
    val isDone: Boolean = false,
    val priority: Byte = 0,
    val startsAt: ZonedDateTime? = null,
    val finishesAt: ZonedDateTime? = null
)

fun CreateTaskRequest.toEntity(): Task =
    Task (
        title = title,
        description = description,
        isDone = isDone,
        priority = priority,
        startsAt = startsAt,
        finishesAt = finishesAt
    )