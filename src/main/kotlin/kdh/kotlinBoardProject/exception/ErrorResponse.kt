package kdh.kotlinBoardProject.exception

import org.springframework.http.ResponseEntity
import java.time.LocalDateTime

class ErrorResponse (
        var status:Int? = 0,
        var error: String? = null,
        var code: String? = null,
        var message: String? = null
    ){
    private val timestamp = LocalDateTime.now()


    companion object {
        fun toResponseEntity(errorCode: ErrorCode): ResponseEntity<ErrorResponse> {
            return ResponseEntity
                    .status(errorCode.httpStatus!!)
                    .body(ErrorResponse(
                        status = errorCode.httpStatus!!.value(),
                        error = errorCode.httpStatus!!.name,
                        code = errorCode.name,
                        message = errorCode.detail
                    ))
        }
    }
}