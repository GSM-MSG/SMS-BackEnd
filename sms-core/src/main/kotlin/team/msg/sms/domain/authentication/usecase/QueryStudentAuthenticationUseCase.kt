package team.msg.sms.domain.authentication.usecase

import org.springframework.transaction.annotation.Transactional
import team.msg.sms.common.annotation.UseCase
import team.msg.sms.domain.authentication.dto.res.QueryStudentAuthenticationListResponseData
import team.msg.sms.domain.authentication.dto.res.RequestAuthenticationResponseData
import team.msg.sms.domain.authentication.dto.res.RequestStudentAuthenticationResponseData
import team.msg.sms.domain.authentication.service.AuthenticationHistoryService
import team.msg.sms.domain.authentication.service.AuthenticationService
import team.msg.sms.domain.student.service.StudentService
import team.msg.sms.domain.user.service.UserService
import java.util.*

@UseCase
class QueryStudentAuthenticationUseCase(
    private val authenticationService: AuthenticationService,
    private val authenticationHistoryService: AuthenticationHistoryService,
    private val studentService: StudentService,
    private val userService: UserService,
) {
    @Transactional(readOnly = true)
    fun execute(studentUuid: String): QueryStudentAuthenticationListResponseData {
        val student = studentService.getStudentById(UUID.fromString(studentUuid))
        val user = userService.getUserById(student.userId)
        val authenticationList = authenticationService.getAuthenticationByStudent(student, user)

        return QueryStudentAuthenticationListResponseData(
            activities = authenticationList.map {
                val history = authenticationHistoryService.getLatestAuthenticationHistory(it, student, user)

                RequestStudentAuthenticationResponseData(
                    id = it.id,
                    title = it.title,
                    lastModifiedDate = history.createdAt.toLocalDate(),
                    activityStatus = it.activityStatus,
                    score = it.score
                )
            }
        )
    }
}