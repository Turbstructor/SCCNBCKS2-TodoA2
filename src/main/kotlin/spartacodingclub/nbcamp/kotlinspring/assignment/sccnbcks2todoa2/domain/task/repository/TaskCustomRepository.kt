package spartacodingclub.nbcamp.kotlinspring.assignment.sccnbcks2todoa2.domain.task.repository

import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Slice
import spartacodingclub.nbcamp.kotlinspring.assignment.sccnbcks2todoa2.domain.task.entity.Task

interface TaskCustomRepository {

    fun findAllByPageableWithQueryDSL(pageable: Pageable): Slice<Task>
    fun findByIdWithQueryDSL(id: Long): Task?
}