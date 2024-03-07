package team.msg.sms.domain.authentication.usecase

import team.msg.sms.common.annotation.UseCase
import team.msg.sms.domain.authentication.exception.AlreadyApprovedAuthenticationException
import team.msg.sms.domain.authentication.model.ActivityStatus
import team.msg.sms.domain.authentication.service.AuthenticationHistoryService
import team.msg.sms.domain.authentication.service.AuthenticationService
import team.msg.sms.domain.student.service.StudentService
import team.msg.sms.domain.user.service.UserService
import java.util.UUID

@UseCase
class DeleteAuthenticationUseCase(
        private val authenticationService: AuthenticationService,
        private val authenticationHistoryService: AuthenticationHistoryService,
        private val studentService: StudentService,
        private val userService: UserService
) {
    fun execute(uuid: String) {
        val authentication = authenticationService.getAuthenticationByUuid(UUID.fromString(uuid))
        val student = studentService.getStudentById(authentication.studentId)
        val user = userService.getUserById(student.userId)

        if(authentication.activityStatus == ActivityStatus.APPROVED ||
           authentication.studentId != student.id) throw AlreadyApprovedAuthenticationException

        authenticationHistoryService.deleteAuthenticationHistoryByAuthentication(authentication, student, user)
        authenticationService.deleteAuthenticationByUuid(uuid)
    }
}