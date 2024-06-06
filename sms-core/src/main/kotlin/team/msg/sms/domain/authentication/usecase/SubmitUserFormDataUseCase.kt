package team.msg.sms.domain.authentication.usecase

import team.msg.sms.common.annotation.UseCase
import team.msg.sms.domain.authentication.dto.req.SubmitUserFormRequestData
import team.msg.sms.domain.authentication.service.UserFormValueService

@UseCase
class SubmitUserFormDataUseCase(
    private val userFormValueService: UserFormValueService
) {
    fun execute(submitData: SubmitUserFormRequestData) {
    }
}