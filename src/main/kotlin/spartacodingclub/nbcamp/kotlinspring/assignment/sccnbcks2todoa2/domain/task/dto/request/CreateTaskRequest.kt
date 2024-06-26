package spartacodingclub.nbcamp.kotlinspring.assignment.sccnbcks2todoa2.domain.task.dto.request

import spartacodingclub.nbcamp.kotlinspring.assignment.sccnbcks2todoa2.domain.task.entity.Task
import java.time.ZonedDateTime

data class CreateTaskRequest (

    val title: String,
    val description: String,
    val isDone: Boolean,
    val priority: Byte,
    val startsAt: ZonedDateTime?,
    val finishesAt: ZonedDateTime?
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