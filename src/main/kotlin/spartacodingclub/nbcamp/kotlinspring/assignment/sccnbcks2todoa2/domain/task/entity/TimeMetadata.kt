package spartacodingclub.nbcamp.kotlinspring.assignment.sccnbcks2todoa2.domain.task.entity

import jakarta.persistence.Column
import jakarta.persistence.Embeddable
import java.time.ZoneId
import java.time.ZonedDateTime

@Embeddable
class TimeMetadata {

    @Column(name = "created_at", nullable = false, updatable = false)
    val createdAt: ZonedDateTime = ZonedDateTime.now(ZoneId.of("UTC"))

    @Column(name = "updated_at", nullable = false)
    var updatedAt: ZonedDateTime = ZonedDateTime.now(ZoneId.of("UTC"))
}