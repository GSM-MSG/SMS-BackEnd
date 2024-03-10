package team.msg.sms.domain.authentication.usecase

import org.springframework.transaction.annotation.Transactional
import team.msg.sms.common.annotation.UseCase
import team.msg.sms.domain.authentication.dto.res.QueryAuthenticationDetailsResponseData
import team.msg.sms.domain.authentication.dto.res.QueryAuthenticationHistoriesResponseData
import team.msg.sms.domain.authentication.dto.res.QueryAuthenticationHistoryResponseData
import team.msg.sms.domain.authentication.service.AuthenticationHistoryService
import team.msg.sms.domain.authentication.service.AuthenticationService
import team.msg.sms.domain.student.service.StudentService
import team.msg.sms.domain.user.service.UserService
import java.util.*

@UseCase
class QueryAuthenticationHistoriesUseCase(
    private val authenticationService: AuthenticationService,
    private val authenticationHistoryService: AuthenticationHistoryService,
    private val studentService: StudentService,
    private val userService: UserService
) {
    @Transactional(readOnly = true)
    fun execute(uuid: String): QueryAuthenticationHistoriesResponseData {
        val authentication = authenticationService.getAuthenticationByUuid(UUID.fromString(uuid))
        val student = studentService.getStudentById(authentication.studentId)
        val user = userService.getUserById(student.userId)
        val histories = authenticationHistoryService.getAuthenticationHistories(authentication, student, user)

        return QueryAuthenticationHistoriesResponseData(
            histories.map { history ->
                QueryAuthenticationHistoryResponseData(
                    authenticationId = history.authenticationId.toString(),
                    teacherName = "나임",
                    gradedDate = history.createdAt.toString(),
                    score = authentication.score,
                    activityStatus = history.activityStatus.name,
                    reason = history.reason
                )
            }
        )
    }
}