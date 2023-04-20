package team.msg.sms.global.error

import org.springframework.http.HttpStatus
import org.springframework.validation.BindException
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice
import team.msg.sms.common.error.SmsException

@RestControllerAdvice
class GlobalExceptionHandler {
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BindException::class)
    protected fun handleBindException(e: BindingResult): ValidationErrorResponse? = ErrorResponse.of(e)
}