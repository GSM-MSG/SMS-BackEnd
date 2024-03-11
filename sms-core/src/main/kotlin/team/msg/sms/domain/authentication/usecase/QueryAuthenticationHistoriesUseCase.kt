package team.msg.sms.domain.authentication.usecase

import org.springframework.transaction.annotation.Transactional
import team.msg.sms.common.annotation.UseCase
import team.msg.sms.domain.auth.model.Role
import team.msg.sms.domain.authentication.dto.res.QueryAuthenticationHistoriesResponseData
import team.msg.sms.domain.authentication.dto.res.QueryAuthenticationHistoryResponseData
import team.msg.sms.domain.authentication.exception.PermissionRoleDeniedException
import team.msg.sms.domain.authentication.service.AuthenticationHistoryService
import team.msg.sms.domain.authentication.service.AuthenticationService
import team.msg.sms.domain.student.service.StudentService
import team.msg.sms.domain.teacher.service.TeacherService
import team.msg.sms.domain.user.service.UserService
import java.time.format.DateTimeFormatter
import java.util.*

@UseCase
class QueryAuthenticationHistoriesUseCase(
    private val authenticationService: AuthenticationService,
    private val authenticationHistoryService: AuthenticationHistoryService,
    private val studentService: StudentService,
    private val userService: UserService,
) {
    @Transactional(readOnly = true)
    fun execute(uuid: String): QueryAuthenticationHistoriesResponseData {
        val allowedRole = arrayOf(Role.ROLE_STUDENT, Role.ROLE_PRINCIPAL, Role.ROLE_DEPUTY_PRINCIPAL, Role.ROLE_DIRECTOR, Role.ROLE_HOMEROOM)
        if (userService.getCurrentUser().roles.none { it in allowedRole}) throw PermissionRoleDeniedException

        val authentication = authenticationService.getAuthenticationByUuid(UUID.fromString(uuid))
        val student = studentService.getStudentById(authentication.studentId)
        val user = userService.getUserById(student.userId)
        val histories = authenticationHistoryService.getAuthenticationHistories(authentication, student, user)


        return QueryAuthenticationHistoriesResponseData(
            histories.map { history ->
                val teacherName = if (history.teacherId != null) {
                    userService.getUserById(history.teacherId).name
                } else null

                QueryAuthenticationHistoryResponseData(
                    authenticationId = history.authenticationId.toString(),
                    teacherName = teacherName,
                    gradedDate = history.createdAt.toLocalDate(),
                    score = authentication.score,
                    activityStatus = history.activityStatus,
                    reason = history.reason
                )
            }
        )
    }
}