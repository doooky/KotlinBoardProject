package kdh.kotlinBoardProject.exception

import org.slf4j.LoggerFactory
import org.springframework.dao.DataIntegrityViolationException
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler
import javax.validation.ConstraintViolationException

@RestControllerAdvice
class GlobalExceptionHandler : ResponseEntityExceptionHandler() {
    private val log = LoggerFactory.getLogger(javaClass)

    @ExceptionHandler(value = [ConstraintViolationException::class, DataIntegrityViolationException::class])
    protected fun handleDataException(): ResponseEntity<ErrorResponse> {
        log.error("handleDataException throw Exception : {}", ErrorCode.DUPLICATE_RESOURCE)
        return ErrorResponse.Companion.toResponseEntity(ErrorCode.DUPLICATE_RESOURCE)
    }

    @ExceptionHandler(value = [CustomException::class])
    protected fun handleCustomException(e: CustomException): ResponseEntity<ErrorResponse> {
        log.error("handleCustomException throw CustomException : {}", e.errorCode)
        return ErrorResponse.Companion.toResponseEntity(e.errorCode!!)
    }
}