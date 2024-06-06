package team.msg.sms.persistence.authentication.repository

import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.stereotype.Repository
import team.msg.sms.persistence.authentication.entity.AuthenticationSectionJpaEntity
import team.msg.sms.persistence.authentication.entity.QAuthenticationSectionJpaEntity
import java.util.*

@Repository
class AuthenticationSectionRepositoryImpl(
    val jpaQueryFactory: JPAQueryFactory
) : AuthenticationSectionRepositoryCustom {
    override fun getAuthenticationSectionByGroupIds(groupIds: List<UUID>): List<AuthenticationSectionJpaEntity> {
        val qAuthenticationSection = QAuthenticationSectionJpaEntity.authenticationSectionJpaEntity

        return jpaQueryFactory
            .select(qAuthenticationSection)
            .from(qAuthenticationSection)
            .where(qAuthenticationSection.groupId.`in`(groupIds))
            .fetch()
            .toList()
    }
}