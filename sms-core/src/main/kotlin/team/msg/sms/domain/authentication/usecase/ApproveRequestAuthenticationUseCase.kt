package team.msg.sms.domain.authentication.usecase

import org.springframework.context.ApplicationEventPublisher
import org.springframework.transaction.annotation.Transactional
import team.msg.sms.common.annotation.UseCase
import team.msg.sms.domain.auth.model.Role
import team.msg.sms.domain.authentication.dto.req.ApproveAuthenticationRequestData
import team.msg.sms.domain.authentication.dto.req.RejectAuthenticationRequestData
import team.msg.sms.domain.authentication.event.AuthenticationHistoryEvent
import team.msg.sms.domain.authentication.exception.AlreadyGivenScoreException
import team.msg.sms.domain.authentication.exception.NoRequestedActivityException
import team.msg.sms.domain.authentication.exception.PermissionRoleDeniedException
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

        val allowedRole = listOf(Role.ROLE_HOMEROOM, Role.ROLE_DIRECTOR, Role.ROLE_PRINCIPAL, Role.ROLE_DEPUTY_PRINCIPAL)
        if(currentUser.roles.none { it in allowedRole }) throw PermissionRoleDeniedException

        if(Role.ROLE_HOMEROOM in currentUser.roles &&
            user.stuNum.substring(0, 1) != "${homeroomTeacher.grade}${homeroomTeacher.classNum}"){
            throw PermissionRoleDeniedException
        }

        if(authentication.score != 0) throw AlreadyGivenScoreException

        if(authentication.activityStatus != ActivityStatus.REQUESTED) throw NoRequestedActivityException

        val updatedAuthentication = Authentication(
            score = approveAuthenticationRequestData.score,
            activityStatus = ActivityStatus.APPROVED,
            id = authentication.id,
            title = authentication.title,
            content = authentication.content,
            activityImages = authentication.activityImages,
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