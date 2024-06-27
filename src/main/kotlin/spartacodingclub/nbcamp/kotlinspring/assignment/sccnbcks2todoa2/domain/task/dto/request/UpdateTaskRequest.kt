package spartacodingclub.nbcamp.kotlinspring.assignment.sccnbcks2todoa2.domain.task.dto.request

import spartacodingclub.nbcamp.kotlinspring.assignment.sccnbcks2todoa2.domain.task.entity.Task
import java.time.ZonedDateTime

data class UpdateTaskRequest (

    val title: String,
    val description: String,
    val isDone: Boolean,
    val priority: Byte,
    val startsAt: ZonedDateTime?,
    val finishesAt: ZonedDateTime?
)

fun UpdateTaskRequest.updateEntity(task: Task) =
    task.update(
        title = this.title,
        description = this.description,
        isDone = this.isDone,
        priority = this.priority,
        startsAt = this.startsAt,
        finishesAt = this.finishesAt
    )