package team.msg.sms.domain.authentication.usecase

import org.springframework.transaction.annotation.Transactional
import team.msg.sms.common.annotation.UseCase
import team.msg.sms.domain.auth.model.Role
import team.msg.sms.domain.authentication.dto.res.QueryAuthenticationDetailsResponseData
import team.msg.sms.domain.authentication.dto.res.QueryRequestedAuthenticationDetailsResponseData
import team.msg.sms.domain.authentication.exception.InvalidGradeClassException
import team.msg.sms.domain.authentication.exception.PermissionRoleDeniedException
import team.msg.sms.domain.authentication.exception.UnsuitableActivityStatusException
import team.msg.sms.domain.authentication.model.ActivityStatus
import team.msg.sms.domain.authentication.service.AuthenticationHistoryService
import team.msg.sms.domain.authentication.service.AuthenticationService
import team.msg.sms.domain.student.service.StudentService
import team.msg.sms.domain.teacher.service.GetHomeroomTeacherService
import team.msg.sms.domain.teacher.service.HomeroomTeacherService
import team.msg.sms.domain.teacher.service.TeacherService
import team.msg.sms.domain.user.service.UserService
import java.util.*

@UseCase
class QueryRequestedAuthenticationDetailsUseCase(
    private val authenticationService: AuthenticationService,
    private val authenticationHistoryService: AuthenticationHistoryService,
    private val homeroomTeacherService: HomeroomTeacherService,
    private val studentService: StudentService,
    private val userService: UserService
) {
    @Transactional(readOnly = true)
    fun execute(uuid: String): QueryRequestedAuthenticationDetailsResponseData {
        val authentication = authenticationService.getAuthenticationByUuid(UUID.fromString(uuid))

        if(authentication.activityStatus != ActivityStatus.REQUESTED)
            throw UnsuitableActivityStatusException

        val user = userService.getCurrentUser()

        when {
            Role.ROLE_HOMEROOM in user.roles -> {
                val homeroomTeacher = homeroomTeacherService.getHomeroomTeacherByUserId(user.id)

                if("${homeroomTeacher.grade}${homeroomTeacher.classNum}" != user.stuNum.substring(0, 1))
                    throw InvalidGradeClassException
            }
            Role.ROLE_PRINCIPAL in user.roles ||
            Role.ROLE_DEPUTY_PRINCIPAL in user.roles ||
            Role.ROLE_DIRECTOR in user.roles -> {}
            else -> throw PermissionRoleDeniedException
        }

        val student = studentService.getStudentById(authentication.studentId)
        val studentUser = userService.getUserById(student.userId)
        val history = authenticationHistoryService.getLatestAuthenticationHistory(authentication, student, studentUser)

        return QueryRequestedAuthenticationDetailsResponseData(
            id = authentication.id,
            title = authentication.title,
            content = authentication.title,
            activityImages = authentication.activityImages,
            lastModifiedDate = history.createdAt.toLocalDate(),
            score = authentication.score
        )
    }
}