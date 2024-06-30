package spartacodingclub.nbcamp.kotlinspring.assignment.sccnbcks2todoa2.domain.task.repository

import org.springframework.stereotype.Repository
import spartacodingclub.nbcamp.kotlinspring.assignment.sccnbcks2todoa2.domain.task.entity.QTask
import spartacodingclub.nbcamp.kotlinspring.assignment.sccnbcks2todoa2.domain.task.entity.Task
import spartacodingclub.nbcamp.kotlinspring.assignment.sccnbcks2todoa2.infra.querydsl.QueryDslModule

@Repository
class TaskRepositoryImpl : TaskCustomRepository, QueryDslModule() {

    private val task = QTask.task

    override fun findAllWithQueryDSL(): List<Task> =
        queryFactory
            .selectFrom(task)
            .fetch()

    override fun findByIdWithQueryDSL(id: Long): Task? =
        queryFactory
            .selectFrom(task)
            .where(task.id.eq(id))
            .fetchOne()

}