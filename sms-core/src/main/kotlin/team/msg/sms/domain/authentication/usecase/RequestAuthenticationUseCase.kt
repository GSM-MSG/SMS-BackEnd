package team.msg.sms.domain.authentication.usecase

import org.springframework.context.ApplicationEventPublisher
import org.springframework.transaction.annotation.Transactional
import team.msg.sms.common.annotation.UseCase
import team.msg.sms.domain.authentication.dto.res.RequestAuthenticationResponseData
import team.msg.sms.domain.authentication.event.AuthenticationHistoryEvent
import team.msg.sms.domain.authentication.model.ActivityStatus
import team.msg.sms.domain.authentication.model.Authentication
import team.msg.sms.domain.authentication.service.AuthenticationService
import team.msg.sms.domain.student.service.StudentService
import team.msg.sms.domain.user.service.UserService
import java.util.*

@UseCase
class RequestAuthenticationUseCase(
    private val authenticationService: AuthenticationService,
    private val studentService: StudentService,
    private val userService: UserService,
    private val applicationEventPublisher: ApplicationEventPublisher
) {
    @Transactional(rollbackFor = [Exception::class])
    fun execute(uuid: String): RequestAuthenticationResponseData {
        val authentication = authenticationService.getAuthenticationByUuid(UUID.fromString(uuid))
            .run {
                Authentication(
                    id = id,
                    title = title,
                    content = content,
                    activityImages = activityImages,
                    studentId = studentId,
                    score = score,
                    activityStatus = ActivityStatus.REQUESTED
                )
            }

        AuthenticationHistoryEvent(
            authentication = authentication,
            reason = "활동이 채점요청되었습니다."
        ).let { applicationEventPublisher.publishEvent(it) }

        val student = studentService.getStudentById(authentication.studentId)
        val user = userService.getUserById(student.userId)

        return RequestAuthenticationResponseData(
            id = authenticationService.save(authentication, student, user).id
        )
    }
}