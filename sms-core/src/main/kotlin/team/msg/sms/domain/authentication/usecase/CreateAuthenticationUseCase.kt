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
class CreateAuthenticationUseCase(
    private val studentService: StudentService,
    private val userService: UserService,
    private val authenticationService: AuthenticationService,
    private val applicationEventPublisher: ApplicationEventPublisher
) {
    @Transactional(rollbackFor = [Exception::class])
    fun execute(createData: CreateAuthenticationRequestData): CreateAuthenticationResponseData {
        val user = userService.getCurrentUser()
        val student = studentService.getStudentByUser(user)

        val authentication = Authentication(
            id = UUID.randomUUID(),
            title = createData.title,
            content = createData.content,
            activityImages = createData.activityImages,
            studentId = student.id
        ).let { authenticationService.save(it, student, user) }

        AuthenticationHistoryEvent(
            authentication = authentication,
            reason = "활동이 저장되었습니다."
        ).let { applicationEventPublisher.publishEvent(it) }

        return CreateAuthenticationResponseData(
            id = authentication.id
        )
    }
}