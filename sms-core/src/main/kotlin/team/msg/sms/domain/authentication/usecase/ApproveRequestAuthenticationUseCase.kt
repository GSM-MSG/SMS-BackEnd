package team.msg.sms.domain.authentication.usecase

import org.springframework.context.ApplicationEvent
import org.springframework.context.ApplicationEventPublisher
import org.springframework.transaction.annotation.Transactional
import team.msg.sms.common.annotation.UseCase
import team.msg.sms.domain.auth.model.Role
import team.msg.sms.domain.authentication.dto.req.ApproveAuthenticationRequestData
import team.msg.sms.domain.authentication.dto.res.RequestAuthenticationResponseData
import team.msg.sms.domain.authentication.event.AuthenticationHistoryEvent
import team.msg.sms.domain.authentication.exception.AlreadyGivenScoreException
import team.msg.sms.domain.authentication.exception.NoRequestedActivityException
import team.msg.sms.domain.authentication.exception.PermissionRoleDeniedException
import team.msg.sms.domain.authentication.exception.UnsuitableActivityStatusException
import team.msg.sms.domain.authentication.model.ActivityStatus
import team.msg.sms.domain.authentication.model.Authentication
import team.msg.sms.domain.authentication.service.AuthenticationService
import team.msg.sms.domain.student.service.StudentService
import team.msg.sms.domain.teacher.service.HomeroomTeacherService
import team.msg.sms.domain.teacher.service.TeacherService
import team.msg.sms.domain.user.service.UserService
import java.util.*

@UseCase
class ApproveRequestAuthenticationUseCase(
    private val authenticationService: AuthenticationService,
    private val studentService: StudentService,
    private val userService: UserService,
    private val applicationEventPublisher: ApplicationEventPublisher,
    private val teacherService: TeacherService,
    private val homeroomTeacherService: HomeroomTeacherService
) {
    @Transactional(rollbackFor = [Exception::class])
    fun execute(approveAuthenticationRequestData: ApproveAuthenticationRequestData, uuid: String) {
        val authentication = authenticationService.getAuthenticationByUuid(UUID.fromString(uuid))
        val student = studentService.getStudentById(authentication.studentId)
        val user = userService.getUserById(student.userId)

        val currentUser = userService.getCurrentUser()
        val teacher = teacherService.getTeacherByUser(currentUser)
        val homeroomTeacher = homeroomTeacherService.getHomeroomTeacherByUserId(currentUser.id)

        if(user.stuNum.substring(0, 1) != "${homeroomTeacher.grade}${homeroomTeacher.classNum}" &&
            Role.ROLE_DIRECTOR !in currentUser.roles &&
            Role.ROLE_HOMEROOM in currentUser.roles){
            throw PermissionRoleDeniedException
        }

        if(authentication.score != 0) throw AlreadyGivenScoreException

        if(authentication.activityStatus != ActivityStatus.REQUESTED) throw NoRequestedActivityException

        val updatedAuthentication = Authentication(
            score = approveAuthenticationRequestData.score,
            id = authentication.id,
            title = authentication.title,
            content = authentication.content,
            activityImages = authentication.activityImages,
            activityStatus = authentication.activityStatus,
            studentId = authentication.studentId,
        )

        authenticationService.save(authentication, student, user)

        applicationEventPublisher.publishEvent(AuthenticationHistoryEvent(
            authentication = updatedAuthentication,
            reason = approveAuthenticationRequestData.reason,
            teacherId = teacher.id
        ))
    }
}