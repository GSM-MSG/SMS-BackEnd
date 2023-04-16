package team.msg.sms.global.error

import org.springframework.validation.BindingResult
import org.springframework.validation.FieldError
import team.msg.sms.common.error.ErrorProperty

data class ErrorResponse(
    val status: Int,
    val message: String
) {
    companion object {
        fun of(errorProperty: ErrorProperty) = ErrorResponse(
            status = errorProperty.status(),
            message = errorProperty.message()
        )

        fun of(e: BindingResult): ValidationErrorResponse {
            val errorMap = HashMap<String, String?>()

            for (error: FieldError in e.fieldErrors) {
                errorMap[error.field] = error.defaultMessage
            }

            return ValidationErrorResponse(
                status = GlobalErrorCode.BAD_REQUEST.status(),
                fieldError = errorMap
            )
        }
    }
}

data class ValidationErrorResponse(
    val status: Int,
    val fieldError: Map<String, String?>
)