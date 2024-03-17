package team.msg.sms.domain.authentication.usecase

import org.springframework.context.ApplicationEventPublisher
import org.springframework.transaction.annotation.Transactional
import team.msg.sms.common.annotation.UseCase
import team.msg.sms.domain.authentication.dto.req.UpdateAuthenticationRequestData
import team.msg.sms.domain.authentication.dto.res.UpdateAuthenticationResponseData
import team.msg.sms.domain.authentication.event.AuthenticationHistoryEvent
import team.msg.sms.domain.authentication.exception.OnlyAccessMyselfException
import team.msg.sms.domain.authentication.exception.UnsuitableActivityStatusException
import team.msg.sms.domain.authentication.model.ActivityStatus
import team.msg.sms.domain.authentication.model.Authentication
import team.msg.sms.domain.authentication.service.AuthenticationService
import team.msg.sms.domain.student.service.StudentService
import team.msg.sms.domain.user.service.UserService
import java.util.*

@UseCase
class UpdateAuthenticationUseCase(
    private val userService: UserService,
    private val studentService: StudentService,
    private val authenticationService: AuthenticationService,
    private val applicationEventPublisher: ApplicationEventPublisher
) {
    @Transactional(rollbackFor = [Exception::class])
    fun execute(uuid: String, updateData: UpdateAuthenticationRequestData): UpdateAuthenticationResponseData {
        val user = userService.getCurrentUser()
        val student = studentService.getStudentByUser(user)

        val authentication = authenticationService.getAuthenticationByUuid(UUID.fromString(uuid))

        if(authentication.studentId == student.id)
            throw OnlyAccessMyselfException

        if(authentication.activityStatus == ActivityStatus.REQUESTED || authentication.activityStatus == ActivityStatus.APPROVED)
            throw UnsuitableActivityStatusException

        Authentication(
            id = UUID.fromString(uuid),
            title = updateData.title,
            content = updateData.content,
            activityImages = updateData.activityImages,
            studentId = student.id
        ).let { authenticationService.save(it, student, user) }

        AuthenticationHistoryEvent(
            authentication = authentication,
            reason = "활동이 수정되었습니다."
        ).let { applicationEventPublisher.publishEvent(it) }

        return UpdateAuthenticationResponseData(
            id = authentication.id
        )
    }
}