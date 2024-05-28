package team.msg.sms.domain.authentication.usecase

import org.springframework.transaction.annotation.Transactional
import team.msg.sms.common.annotation.UseCase
import team.msg.sms.domain.authentication.dto.res.QueryAuthenticationDetailsResponseData
import team.msg.sms.domain.authentication.service.AuthenticationHistoryService
import team.msg.sms.domain.authentication.service.AuthenticationService
import team.msg.sms.domain.student.service.StudentService
import team.msg.sms.domain.user.service.UserService
import java.util.*

@UseCase
class QueryAuthenticationDetailsUseCase(
    private val authenticationService: AuthenticationService,
    private val authenticationHistoryService: AuthenticationHistoryService,
    private val studentService: StudentService,
    private val userService: UserService
) {
    @Transactional(readOnly = true)
    fun execute(uuid: String): QueryAuthenticationDetailsResponseData {
        val authentication = authenticationService.getAuthenticationByUuid(UUID.fromString(uuid))
        val student = studentService.getStudentById(authentication.studentId)
        val user = userService.getUserById(student.userId)
        val history = authenticationHistoryService.getLatestAuthenticationHistory(authentication, student, user)

        return QueryAuthenticationDetailsResponseData(
            id = authentication.id,
            title = authentication.title,
            content = authentication.title,
            activityImages = authentication.activityImages,
            lastModifiedDate = history.createdAt.toLocalDate(),
            score = authentication.score,
            activityStatus = authentication.activityStatus
        )
    }
}