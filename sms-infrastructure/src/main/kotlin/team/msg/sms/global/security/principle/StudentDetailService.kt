package team.msg.sms.global.security.principle

import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Component
import team.msg.sms.domain.student.spi.StudentPort
import team.msg.sms.global.security.exception.InvalidTokenException
import java.util.*

@Component
class StudentDetailService(
    private val studentPort: StudentPort
) : UserDetailsService {
    override fun loadUserByUsername(username: String?): UserDetails {
        val studentId = UUID.fromString(username)

        if (studentPort.existsStudentById(studentId))
            throw InvalidTokenException

        return StudentDetail(studentId)
    }
}