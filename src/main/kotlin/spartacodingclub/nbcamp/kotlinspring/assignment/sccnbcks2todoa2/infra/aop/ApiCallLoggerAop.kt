package spartacodingclub.nbcamp.kotlinspring.assignment.sccnbcks2todoa2.infra.aop

import io.github.oshai.kotlinlogging.KotlinLogging
import org.aspectj.lang.JoinPoint
import org.aspectj.lang.annotation.*
import org.aspectj.lang.reflect.MethodSignature
import org.springframework.stereotype.Component

@Aspect
@Component
class ApiCallLoggerAop {

    private val logger = KotlinLogging.logger {}

    @Pointcut("execution(* spartacodingclub.nbcamp.kotlinspring.assignment.sccnbcks2todoa2.domain.*.controller.*.*(..))")
    fun cut() {}

    @Before("cut()")
    fun before(
        joinPoint: JoinPoint
    ) {

        (joinPoint.signature as MethodSignature).method.name.let { methodName ->
            logger.info { "Controller method \"$methodName\" starting execution" }
        }
    }

    @AfterReturning(
        value = "cut()",
        returning = "obj"
    )
    fun afterReturning(
        joinPoint: JoinPoint,
        obj: Any
    ) {

        (joinPoint.signature as MethodSignature).method.name.let { methodName ->
            logger.info { "Controller method \"$methodName\" returning value: " +
                    "$obj"
            }
        }
    }

    @AfterThrowing(
        value = "cut()",
        throwing = "ex"
    )
    fun afterThrowing(
        joinPoint: JoinPoint,
        ex: Exception
    ) {

        (joinPoint.signature as MethodSignature).method.name.let { methodName ->
            logger.info { "Controller method \"$methodName\" throwing exception: " +
                    "${ex.javaClass.canonicalName}: " +
                    "${ex.message}"
            }
        }
    }
}