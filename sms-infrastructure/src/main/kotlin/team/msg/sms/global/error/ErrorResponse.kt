package team.msg.sms.global.error

import org.springframework.validation.BindingResult
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
            val errorMap = e.fieldErrors.associate { it.field to it.defaultMessage }

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