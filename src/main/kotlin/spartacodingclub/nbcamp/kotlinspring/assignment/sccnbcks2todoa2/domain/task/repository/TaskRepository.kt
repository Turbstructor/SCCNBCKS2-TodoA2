package spartacodingclub.nbcamp.kotlinspring.assignment.sccnbcks2todoa2.domain.task.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import spartacodingclub.nbcamp.kotlinspring.assignment.sccnbcks2todoa2.domain.task.entity.Task

@Repository
interface TaskRepository : JpaRepository<Task, Long> {
}