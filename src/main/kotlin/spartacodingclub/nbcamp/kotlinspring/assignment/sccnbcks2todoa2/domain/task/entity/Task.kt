package spartacodingclub.nbcamp.kotlinspring.assignment.sccnbcks2todoa2.domain.task.entity

import jakarta.persistence.*
import java.time.ZoneId
import java.time.ZonedDateTime

@Entity
@Table(name = "task")
class Task (

    @Column(name = "title", nullable = false)
    var title: String,

    @Column(name = "description", nullable = false)
    var description: String = "",

    @Column(name = "is_done", nullable = false)
    var isDone: Boolean = false,

    @Column(name = "priority", nullable = false)
    var priority: Byte = 0,

    @Column(name = "started_at", nullable = true)
    var startsAt: ZonedDateTime? = null,

    @Column(name = "finishs_at", nullable = true)
    var finishesAt: ZonedDateTime? = null
) {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    @Embedded
    val time: TimeMetadata = TimeMetadata()


    private fun logUpdateTime() {
        time.updatedAt = ZonedDateTime.now(ZoneId.of("UTC"))
    }

    fun update(
        title: String,
        description: String,
        isDone: Boolean,
        priority: Byte,
        startsAt: ZonedDateTime?,
        finishesAt: ZonedDateTime?
    ) {

        this.title = title
        this.description = description
        this.isDone = isDone
        this.priority = priority
        this.startsAt = startsAt
        this.finishesAt = finishesAt

        this.logUpdateTime()
    }

    fun toggleTaskCompletion() {
        this.isDone = !this.isDone

        this.logUpdateTime()
    }

}