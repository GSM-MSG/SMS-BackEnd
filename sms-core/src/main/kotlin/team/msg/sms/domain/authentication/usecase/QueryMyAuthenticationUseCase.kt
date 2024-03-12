package team.msg.sms.domain.authentication.usecase

import org.springframework.transaction.annotation.Transactional
import team.msg.sms.common.annotation.UseCase
import team.msg.sms.domain.auth.model.Role
import team.msg.sms.domain.authentication.dto.req.FiltersRequestData
import team.msg.sms.domain.authentication.dto.res.QueryMyAuthenticationListResponseData
import team.msg.sms.domain.authentication.dto.res.QueryRequestedAuthenticationListResponseData
import team.msg.sms.domain.authentication.dto.res.RequestMyAuthenticationResponseData
import team.msg.sms.domain.authentication.dto.res.RequestedAuthenticationResponseData
import team.msg.sms.domain.authentication.exception.PermissionRoleDeniedException
import team.msg.sms.domain.authentication.service.AuthenticationHistoryService
import team.msg.sms.domain.authentication.service.AuthenticationService
import team.msg.sms.domain.student.service.StudentService
import team.msg.sms.domain.teacher.service.HomeroomTeacherService
import team.msg.sms.domain.user.service.UserService
import team.msg.sms.domain.authentication.model.Authentication.AuthenticationWithPageInfo as AuthenticationWithPageInfo
import team.msg.sms.domain.authentication.model.Authentication.AuthenticationWithStudentInfoAndRequestedTime as AuthenticationWithStudentInfoAndRequestedTime

@UseCase
class QueryMyAuthenticationUseCase(
    private val authenticationService: AuthenticationService,
    private val authenticationHistoryService: AuthenticationHistoryService,
    private val studentService: StudentService,
    private val userService: UserService
) {
    @Transactional(readOnly = true)
    fun execute(): QueryMyAuthenticationListResponseData {
        val user = userService.getCurrentUser()
        val student = studentService.getStudentByUser(user)
        val authenticationList = authenticationService.getAuthenticationByStudent(student, user)

        return QueryMyAuthenticationListResponseData(
            activities = authenticationList.map { authentication ->
                val latestHistory = authenticationHistoryService.getLatestAuthenticationHistory(authentication, student, user)

                RequestMyAuthenticationResponseData(
                    id = authentication.id,
                    title = authentication.title,
                    score = authentication.score,
                    activityStatus = authentication.activityStatus,
                    lastModifiedDate = latestHistory.createdAt.toLocalDate()
                )
            }
        )
    }
}