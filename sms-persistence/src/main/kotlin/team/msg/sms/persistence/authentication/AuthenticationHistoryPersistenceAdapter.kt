package team.msg.sms.persistence.authentication

import org.springframework.stereotype.Component
import team.msg.sms.domain.authentication.model.Authentication
import team.msg.sms.domain.authentication.model.AuthenticationHistory
import team.msg.sms.domain.authentication.spi.AuthenticationHistoryPort
import team.msg.sms.domain.student.model.Student
import team.msg.sms.domain.user.model.User
import team.msg.sms.persistence.authentication.mapper.toDomain
import team.msg.sms.persistence.authentication.mapper.toEntity
import team.msg.sms.persistence.authentication.repository.AuthenticationHistoryJpaRepository
import team.msg.sms.persistence.student.mapper.toEntity
import team.msg.sms.persistence.user.mapper.toEntity

@Component
class AuthenticationHistoryPersistenceAdapter(
    private val authenticationHistoryJpaRepository: AuthenticationHistoryJpaRepository
) : AuthenticationHistoryPort {
    override fun save(
        authenticationHistory: AuthenticationHistory,
        authentication: Authentication,
        student: Student,
        user: User
    ): AuthenticationHistory =
        authenticationHistoryJpaRepository.save(authenticationHistory.toEntity(authentication.toEntity(student.toEntity(user.toEntity())))).toDomain()

    override fun deleteAuthenticationHistory(authentication: Authentication, student: Student, user: User) =
        authenticationHistoryJpaRepository.deleteAuthenticationHistoryJpaEntityByAuthentication(authentication.toEntity(student.toEntity(user.toEntity())))


    override fun queryLatestAuthenticationHistory(
        authentication: Authentication,
        student: Student,
        user: User
    ): AuthenticationHistory =
        authenticationHistoryJpaRepository.findByAuthenticationOrderByCreatedAtDesc(authentication.toEntity(student.toEntity(user.toEntity()))).toDomain()

    override fun queryAuthenticationHistories(
        authentication: Authentication,
        student: Student,
        user: User
    ): List<AuthenticationHistory> =
        authenticationHistoryJpaRepository.findByAuthentication(authentication.toEntity(student.toEntity(user.toEntity())))
            .map {historyJpaEntity -> historyJpaEntity.toDomain() }
}