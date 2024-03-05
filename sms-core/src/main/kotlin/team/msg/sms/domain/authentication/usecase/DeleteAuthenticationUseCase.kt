package team.msg.sms.domain.authentication.usecase

import org.springframework.context.ApplicationEventPublisher
import org.springframework.transaction.annotation.Transactional
import team.msg.sms.common.annotation.UseCase
import team.msg.sms.domain.authentication.dto.req.CreateAuthenticationRequestData
import team.msg.sms.domain.authentication.dto.res.CreateAuthenticationResponseData
import team.msg.sms.domain.authentication.event.AuthenticationHistoryEvent
import team.msg.sms.domain.authentication.model.Authentication
import team.msg.sms.domain.authentication.service.AuthenticationService
import team.msg.sms.domain.student.service.StudentService
import team.msg.sms.domain.user.service.UserService
import java.util.*

@UseCase
class DeleteAuthenticationUseCase(
    private val studentService: StudentService
) {
    fun execute(uuid: String) {
        studentService.deleteByUuid(UUID.fromString(uuid))
    }
}