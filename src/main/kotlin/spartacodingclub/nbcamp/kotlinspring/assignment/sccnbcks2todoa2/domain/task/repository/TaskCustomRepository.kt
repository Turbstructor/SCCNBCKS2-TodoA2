package spartacodingclub.nbcamp.kotlinspring.assignment.sccnbcks2todoa2.domain.task.repository

import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Slice
import spartacodingclub.nbcamp.kotlinspring.assignment.sccnbcks2todoa2.domain.task.dto.request.ReadTaskRequest
import spartacodingclub.nbcamp.kotlinspring.assignment.sccnbcks2todoa2.domain.task.entity.Task

interface TaskCustomRepository {

    fun findAllByPageableWithQueryDSL(pageable: Pageable, queries: ReadTaskRequest): Slice<Task>
    fun findByIdWithQueryDSL(id: Long): Task?
}