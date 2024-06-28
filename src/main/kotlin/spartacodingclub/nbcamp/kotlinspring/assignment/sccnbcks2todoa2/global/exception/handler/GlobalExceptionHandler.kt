package spartacodingclub.nbcamp.kotlinspring.assignment.sccnbcks2todoa2.global.exception.handler

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import spartacodingclub.nbcamp.kotlinspring.assignment.sccnbcks2todoa2.global.exception.dto.response.ErrorResponse

@RestControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(RuntimeException::class)
    fun handleRuntimeException(
        ex: RuntimeException
    ): ResponseEntity<ErrorResponse> =
        ResponseEntity
            .status(HttpStatus.BAD_REQUEST)
            .body(ErrorResponse(ex.message ?: ""))

}