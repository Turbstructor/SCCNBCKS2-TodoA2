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

fun UpdateTaskRequest.updateEntity(task: Task) {
    task.title = this.title
    task.description = this.description
    task.isDone = this.isDone
    task.priority = this.priority
    task.startsAt = this.startsAt
    task.finishesAt = this.finishesAt

    task.update()
}