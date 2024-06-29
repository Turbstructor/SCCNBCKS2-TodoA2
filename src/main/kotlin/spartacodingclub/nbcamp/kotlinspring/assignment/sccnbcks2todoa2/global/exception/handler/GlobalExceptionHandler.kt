package spartacodingclub.nbcamp.kotlinspring.assignment.sccnbcks2todoa2.global.exception.handler

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import spartacodingclub.nbcamp.kotlinspring.assignment.sccnbcks2todoa2.global.exception.dto.response.ErrorResponse
import spartacodingclub.nbcamp.kotlinspring.assignment.sccnbcks2todoa2.global.exception.type.NonExistentEntityException

@RestControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(NonExistentEntityException::class)
    fun handleRuntimeException(
        ex: RuntimeException
    ): ResponseEntity<ErrorResponse> =
        ResponseEntity
            .status(HttpStatus.NOT_FOUND)
            .body(ErrorResponse(ex.message))

}