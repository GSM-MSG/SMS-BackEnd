package team.msg.sms.persistence.authentication

import org.springframework.stereotype.Component
import team.msg.sms.domain.authentication.model.Authentication
import team.msg.sms.domain.authentication.spi.AuthenticationPort
import team.msg.sms.domain.student.model.Student
import team.msg.sms.domain.user.model.User
import team.msg.sms.persistence.authentication.mapper.toDomain
import team.msg.sms.persistence.authentication.mapper.toEntity
import team.msg.sms.persistence.authentication.repository.AuthenticationJpaRepository
import team.msg.sms.persistence.student.mapper.toEntity
import team.msg.sms.persistence.user.mapper.toEntity

@Component
class AuthenticationPersistenceAdapter(
    private val authenticationJpaRepository: AuthenticationJpaRepository
) : AuthenticationPort {
    override fun save(
        authentication: Authentication,
        student: Student,
        user: User
    ): Authentication =
        authenticationJpaRepository.save(authentication.toEntity(student.toEntity(user.toEntity()))).toDomain()

}