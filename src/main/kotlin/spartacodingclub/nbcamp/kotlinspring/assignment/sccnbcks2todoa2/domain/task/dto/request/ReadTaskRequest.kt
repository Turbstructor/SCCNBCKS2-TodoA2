package spartacodingclub.nbcamp.kotlinspring.assignment.sccnbcks2todoa2.domain.task.dto.request

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.annotation.JsonNaming

@JsonNaming(PropertyNamingStrategies.KebabCaseStrategy::class)
@JsonIgnoreProperties(ignoreUnknown = true)
data class ReadTaskRequest(

    val titleContains: String? = null,
    val descriptionContains: String? = null,
    val isDone: String? = null,
    val minPriority: String? = null,
    val maxPriority: String? = null,
    val notStarted: String? = null,
    val notFinished: String? = null
)
