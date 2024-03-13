package team.msg.sms.persistence.authentication

import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component
import team.msg.sms.domain.authentication.model.ActivityStatus
import team.msg.sms.domain.authentication.model.Authentication
import team.msg.sms.domain.authentication.spi.AuthenticationPort
import team.msg.sms.domain.student.model.Student
import team.msg.sms.domain.user.model.User
import team.msg.sms.persistence.authentication.mapper.toDomain
import team.msg.sms.persistence.authentication.mapper.toDomainWithUserInfoAndRequestedTime
import team.msg.sms.persistence.authentication.mapper.toEntity
import team.msg.sms.persistence.authentication.repository.AuthenticationJpaRepository
import team.msg.sms.persistence.student.mapper.toEntity
import team.msg.sms.persistence.user.mapper.toEntity
import java.util.*
import team.msg.sms.persistence.authentication.entity.QAuthenticationHistoryJpaEntity.authenticationHistoryJpaEntity as history
import team.msg.sms.persistence.authentication.entity.QAuthenticationJpaEntity.authenticationJpaEntity as authentication

@Component
class AuthenticationPersistenceAdapter(
    private val authenticationJpaRepository: AuthenticationJpaRepository,
    private val queryFactory: JPAQueryFactory
) : AuthenticationPort {
    override fun save(
        authentication: Authentication,
        student: Student,
        user: User
    ): Authentication =
        authenticationJpaRepository.save(authentication.toEntity(student.toEntity(user.toEntity()))).toDomain()

    override fun queryAuthenticationByUuid(uuid: UUID): Authentication? =
        authenticationJpaRepository.findByIdOrNull(uuid)?.toDomain()

    override fun deleteAuthenticationByUuid(uuid: UUID) {
        authenticationJpaRepository.deleteById(uuid)
    }

    override fun queryMyAuthenticationByStudent(student: Student, user: User): List<Authentication> {
        return authenticationJpaRepository.findByStudent(student.toEntity(user.toEntity()))
            .map { authenticationJpaEntity -> authenticationJpaEntity.toDomain() }
    }

    override fun queryRequestedAuthentications(): List<Authentication.AuthenticationWithStudentInfoAndRequestedTime> =
        queryFactory.select(authentication,history.createdAt)
            .from(authentication)
            .leftJoin(history).on(authentication.eq(history.authentication))
            .where(
                authentication.activityStatus.eq(ActivityStatus.REQUESTED),
                history.createdAt.eq(queryLastedAuthentication())
            )
            .fetch()
            .map {
                it[authentication]!!.toDomainWithUserInfoAndRequestedTime(it[history.createdAt]!!)
            }

    private fun queryLastedAuthentication() =
        queryFactory
            .select(history.createdAt.max())
            .from(history)
            .where(
                history.authentication.eq(authentication))
}