package spartacodingclub.nbcamp.kotlinspring.assignment.sccnbcks2todoa2.domain.task.repository

import spartacodingclub.nbcamp.kotlinspring.assignment.sccnbcks2todoa2.domain.task.entity.Task

interface TaskCustomRepository {

    fun findAllWithQueryDSL(): List<Task>
    fun findByIdWithQueryDSL(id: Long): Task?
}