package spartacodingclub.nbcamp.kotlinspring.assignment.sccnbcks2todoa2.domain.task.repository

import com.querydsl.core.types.Expression
import com.querydsl.core.types.Order
import com.querydsl.core.types.OrderSpecifier
import com.querydsl.core.types.dsl.EntityPathBase
import com.querydsl.core.types.dsl.PathBuilder
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Slice
import org.springframework.data.domain.SliceImpl
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Repository
import spartacodingclub.nbcamp.kotlinspring.assignment.sccnbcks2todoa2.domain.task.entity.QTask
import spartacodingclub.nbcamp.kotlinspring.assignment.sccnbcks2todoa2.domain.task.entity.Task
import spartacodingclub.nbcamp.kotlinspring.assignment.sccnbcks2todoa2.infra.querydsl.QueryDslModule

@Repository
class TaskRepositoryImpl : TaskCustomRepository, QueryDslModule() {

    private val task = QTask.task

    override fun findAllByPageableWithQueryDSL(pageSettings: Pageable): Slice<Task> {

        val totalCount = queryFactory.select(task.count()).from(task).fetchOne() ?: 0L
        val hasNext = (pageSettings.offset + pageSettings.pageSize) < totalCount
        val tasks = queryFactory
            .selectFrom(task)
            .offset(pageSettings.offset)
            .limit(pageSettings.pageSize.toLong())
            .orderBy(*orderSpecifiers(pageSettings, task))
            .fetch()

        return SliceImpl(tasks, pageSettings, hasNext)
    }

    override fun findByIdWithQueryDSL(id: Long): Task? =
        queryFactory
            .selectFrom(task)
            .where(task.id.eq(id))
            .fetchOne()

    private fun orderSpecifiers(pageSettings: Pageable, path: EntityPathBase<*>): Array<OrderSpecifier<*>> {

        val pathBuilder = PathBuilder(path.type, path.metadata)

        return pageSettings.sort.toList().map { each ->
            OrderSpecifier(
                if (each.isAscending) Order.ASC else Order.DESC,
                pathBuilder.get(each.property) as Expression<Comparable<*>>
            )
        }.toTypedArray()
    }

}