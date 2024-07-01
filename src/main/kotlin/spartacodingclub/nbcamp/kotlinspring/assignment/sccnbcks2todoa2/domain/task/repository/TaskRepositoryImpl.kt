package spartacodingclub.nbcamp.kotlinspring.assignment.sccnbcks2todoa2.domain.task.repository

import com.querydsl.core.BooleanBuilder
import com.querydsl.core.types.Expression
import com.querydsl.core.types.Order
import com.querydsl.core.types.OrderSpecifier
import com.querydsl.core.types.dsl.BooleanExpression
import com.querydsl.core.types.dsl.EntityPathBase
import com.querydsl.core.types.dsl.PathBuilder
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Slice
import org.springframework.data.domain.SliceImpl
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Repository
import spartacodingclub.nbcamp.kotlinspring.assignment.sccnbcks2todoa2.domain.task.dto.request.ReadTaskRequest
import spartacodingclub.nbcamp.kotlinspring.assignment.sccnbcks2todoa2.domain.task.entity.QTask
import spartacodingclub.nbcamp.kotlinspring.assignment.sccnbcks2todoa2.domain.task.entity.Task
import spartacodingclub.nbcamp.kotlinspring.assignment.sccnbcks2todoa2.infra.querydsl.QueryDslModule
import java.time.ZoneOffset
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

@Repository
class TaskRepositoryImpl : TaskCustomRepository, QueryDslModule() {

    private val task = QTask.task

    override fun findAllByPageableWithQueryDSL(
        pageSettings: Pageable,
        queries: ReadTaskRequest
    ): Slice<Task> {

        val allConditions = allConditions(queries)


        val tasksCount = queryFactory
            .selectFrom(task)
            .where(allConditions)
            .orderBy(*orderSpecifiers(pageSettings, task))
            .fetch()
            .count()

        val tasks = queryFactory
            .selectFrom(task)
            .where(allConditions)
            .offset(pageSettings.offset)
            .limit(pageSettings.pageSize.toLong())
            .orderBy(*orderSpecifiers(pageSettings, task))
            .fetch()

        val hasNext = (pageSettings.offset + pageSettings.pageSize) < tasksCount

        return SliceImpl(tasks, pageSettings, hasNext)
    }

    override fun findByIdWithQueryDSL(
        id: Long
    ): Task? =
        queryFactory
            .selectFrom(task)
            .where(task.id.eq(id))
            .fetchOne()


    private fun orderSpecifiers(
        pageSettings: Pageable,
        path: EntityPathBase<*>
    ): Array<OrderSpecifier<*>> {

        val pathBuilder = PathBuilder(path.type, path.metadata)

        return pageSettings.sort.toList().map { each ->
            OrderSpecifier(
                if (each.isAscending) Order.ASC else Order.DESC,
                pathBuilder.get(each.property) as Expression<Comparable<*>>
            )
        }.toTypedArray()
    }


    private fun allConditions(
        queries: ReadTaskRequest
    ) = BooleanBuilder()
        .and( titleContaining(queries.titleContains) )
        .and( descriptionContaining(queries.descriptionContains) )
        .and( isDone(queries.isDone?.toBoolean()) )
        .and( priorityAtMinimum(queries.minPriority?.toByte()) )
        .and( priorityAtMaximum(queries.maxPriority?.toByte()) )
        .and( queries.notStarted?.toBoolean()?.let { notStartedFlag ->
            ZonedDateTime.now(ZoneOffset.UTC).let { current ->
                if (notStartedFlag) startsAtAfter(current)
                else startsAtBefore(current)
            }
        })
        .and( queries.notFinished?.toBoolean()?.let { notFinishedFlag ->
            ZonedDateTime.now(ZoneOffset.UTC).let { current ->
                if (notFinishedFlag) finishesAtAfter(current)
                else finishesAtBefore(current)
            }
        })

    private fun titleContaining(
        keyword: String?
    ): BooleanExpression? =
        keyword?.let { task.title.contains(it) }

    private fun descriptionContaining(
        keyword: String?
    ): BooleanExpression? =
        keyword?.let { task.description.contains(it) }

    private fun isDone(
        isDone: Boolean?
    ): BooleanExpression? =
        isDone?.let { task.isDone.eq(it) }

    private fun priorityAtMinimum(
        minPriority: Byte?
    ): BooleanExpression? =
        minPriority?.let { task.priority.goe(it) }

    private fun priorityAtMaximum(
        maxPriority: Byte?
    ): BooleanExpression? =
        maxPriority?.let { task.priority.loe(it) }

    private fun finishesAtBefore(
        deadline: ZonedDateTime?
    ): BooleanExpression? =
        deadline?.let { task.finishesAt.before(it) }

    private fun finishesAtAfter(
        deadline: ZonedDateTime?
    ): BooleanExpression? =
        deadline?.let { task.finishesAt.after(it) }


    private fun startsAtBefore(
        time: ZonedDateTime?
    ): BooleanExpression? =
        time?.let { task.startsAt.before(it) }

    private fun startsAtAfter(
        time: ZonedDateTime?
    ): BooleanExpression? =
        time?.let { task.startsAt.after(it) }

}