package spartacodingclub.nbcamp.kotlinspring.assignment.sccnbcks2todoa2.infra.querydsl

import com.querydsl.jpa.impl.JPAQueryFactory
import jakarta.persistence.EntityManager
import jakarta.persistence.PersistenceContext

abstract class QueryDslModule {

    @PersistenceContext
    protected lateinit var entityManager: EntityManager

    protected val queryFactory: JPAQueryFactory
        get() = JPAQueryFactory(entityManager)

}