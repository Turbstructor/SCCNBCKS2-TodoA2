package spartacodingclub.nbcamp.kotlinspring.assignment.sccnbcks2todoa2.domain.task.repository

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.date.plusOrMinus
import io.kotest.matchers.equals.shouldBeEqual
import io.kotest.matchers.equals.shouldNotBeEqual
import io.kotest.matchers.nulls.shouldNotBeNull
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.data.repository.findByIdOrNull
import spartacodingclub.nbcamp.kotlinspring.assignment.sccnbcks2todoa2.domain.task.entity.Task
import kotlin.time.Duration.Companion.microseconds
import kotlin.time.Duration.Companion.milliseconds
import kotlin.time.Duration.Companion.minutes

@DataJpaTest
class TaskRepositoryTest @Autowired constructor (
    private val taskRepository: TaskRepository
) : BehaviorSpec({

    afterTest {
        taskRepository.deleteAll()
    }

    val simpleTask = Task(
        title = "Simple task",
        description = "Description about this task"
    )

    given("A task") {

        `when`("when created") {
            taskRepository.save(simpleTask).let { savedTask ->

                then("should not be null") {
                    savedTask shouldNotBe null
                }

                then("should be same as the original") {
                    savedTask.title shouldBeEqual simpleTask.title
                    savedTask.description shouldBeEqual simpleTask.description
                    savedTask.isDone shouldBeEqual simpleTask.isDone
                    savedTask.priority shouldBeEqual simpleTask.priority
                    savedTask.startsAt shouldBe simpleTask.startsAt
                    savedTask.finishesAt shouldBe simpleTask.finishesAt
                    savedTask.time.updatedAt shouldBe savedTask.time.createdAt
                }
            }
        }
    }

    given("A (previously saved) task") {
        val originalTask = taskRepository.save(simpleTask)

        `when`("when retrieved") {
            val retrievedTask = taskRepository.findByIdOrNull(originalTask.id!!)

            then("should not be null") {
                retrievedTask shouldNotBe null
            }

            then("should be same as the original") {
                retrievedTask!!.let {
                    it.title shouldBeEqual originalTask.title
                    it.description shouldBeEqual originalTask.description
                    it.isDone shouldBe originalTask.isDone
                    it.priority shouldBeEqual originalTask.priority
                    it.startsAt shouldBe originalTask.startsAt
                    it.finishesAt shouldBe originalTask.finishesAt
                    it.time.createdAt shouldBe (originalTask.time.createdAt plusOrMinus 1.microseconds)
                    it.time.updatedAt shouldBe (originalTask.time.updatedAt plusOrMinus 1.microseconds)
                }
            }
        }

        `when`("when updated") {
            val newTitle = "Changed Title"
            val newDescription = "Changed Description"

            originalTask.title = newTitle
            originalTask.description = newDescription

            val savedTask = taskRepository.save(originalTask)

            then("should not be null") {
                savedTask shouldNotBe null
            }

            then("should be different from the original") {
                savedTask.title shouldNotBeEqual simpleTask.title
                savedTask.description shouldNotBeEqual simpleTask.description
            }

            then("should have applied changes") {
                savedTask.title shouldBeEqual newTitle
                savedTask.description shouldBeEqual newDescription
            }

            then("should have updatedAt changed") {
                savedTask.time.updatedAt shouldNotBe (originalTask.time.updatedAt plusOrMinus 1.microseconds)
            }
        }

        `when`("when removed") {
            taskRepository.delete(originalTask)

            then("should not be retrieved") {
                taskRepository.findByIdOrNull(originalTask.id!!) shouldBe null
            }
        }
    }
})