package team.msg.sms.domain.authentication.handler

import org.springframework.stereotype.Component
import org.springframework.transaction.event.TransactionPhase
import org.springframework.transaction.event.TransactionalEventListener
import team.msg.sms.domain.authentication.event.AuthenticationHistoryEvent
import team.msg.sms.domain.authentication.model.AuthenticationHistory
import team.msg.sms.domain.authentication.spi.AuthenticationHistoryPort
import team.msg.sms.domain.student.service.StudentService
import team.msg.sms.domain.user.service.UserService

@Component
class AuthenticationEventHandler(
    private val authenticationHistoryPort: AuthenticationHistoryPort,
    private val studentService: StudentService,
    private val userService: UserService
) {

    /**
     * authentication history event가 발행되면 히스토리를 저장하는 핸들러입니다.
     * @param authentication history event
     */
    @TransactionalEventListener(phase = TransactionPhase.BEFORE_COMMIT)
    fun authenticationHistoryHandler(event: AuthenticationHistoryEvent) {
        val authentication = event.authentication
        val student = studentService.getStudentById(authentication.studentId)
        val studentUser = userService.getUserById(student.userId)

        val history = AuthenticationHistory(
            reason = event.reason,
            activityStatus = authentication.activityStatus,
            teacherId = event.teacherId,
            authenticationId = authentication.id
        )

        authenticationHistoryPort.save(history, authentication, student, studentUser)
    }
}