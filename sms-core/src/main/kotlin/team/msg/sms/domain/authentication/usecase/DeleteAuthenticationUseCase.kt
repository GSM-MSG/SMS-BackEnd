package team.msg.sms.domain.authentication.usecase

import team.msg.sms.common.annotation.UseCase
import team.msg.sms.domain.authentication.exception.AlreadyAwardedScoreException
import team.msg.sms.domain.authentication.model.ActivityStatus
import team.msg.sms.domain.authentication.service.AuthenticationService
import team.msg.sms.domain.student.service.StudentService
import java.util.UUID

@UseCase
class DeleteAuthenticationUseCase(
    private val authenticationService: AuthenticationService,
    private val studentService: StudentService
) {
    fun execute(uuid: String) {
        val authentication = authenticationService.getAuthenticationByUuid(UUID.fromString(uuid))
        val student = studentService.currentStudent()

        if(authentication.activityStatus == ActivityStatus.APPROVED ||
           authentication.studentId != student.id) throw AlreadyAwardedScoreException

        authenticationService.deleteAuthenticationByUuid(uuid)
    }
}