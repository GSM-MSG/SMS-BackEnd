package team.msg.sms.global.security.principle

import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Component
import team.msg.sms.domain.user.spi.UserPort
import team.msg.sms.global.security.exception.InvalidTokenException
import java.util.*

@Component
class TeacherDetailService(
    private val userPort: UserPort
): UserDetailsService {
    override fun loadUserByUsername(username: String?): UserDetails {
        val studentId = UUID.fromString(username)

        if (!userPort.existsUserById(studentId))
            throw InvalidTokenException

        return TeacherDetail(studentId)
    }
}