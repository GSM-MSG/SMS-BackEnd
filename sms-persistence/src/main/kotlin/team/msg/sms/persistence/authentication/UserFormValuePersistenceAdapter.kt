package team.msg.sms.persistence.authentication

import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.stereotype.Component
import team.msg.sms.domain.authentication.model.UserFormValue
import team.msg.sms.domain.authentication.spi.UserFormValuePort
import team.msg.sms.persistence.authentication.entity.QUserFormValueJpaEntity
import team.msg.sms.persistence.authentication.mapper.toDomain
import team.msg.sms.persistence.authentication.mapper.toEntity
import team.msg.sms.persistence.authentication.repository.UserFormValueRepository
import java.util.*

@Component
class UserFormValuePersistenceAdapter(
    private val userFormValueRepository: UserFormValueRepository,
    private val jpaQueryFactory: JPAQueryFactory
) : UserFormValuePort {
    override fun queryUserFormValueListByFieldIdAndStudentId(fieldId: UUID, studentId: UUID): List<UserFormValue> {
        return userFormValueRepository.findAllByAuthenticationFieldIdAndCreatedBy(fieldId, studentId).map { it.toDomain() }
    }
    override fun existsUserFormValueBySetIds(setIds: List<UUID>): Boolean {
        val qUserFormValue = QUserFormValueJpaEntity.userFormValueJpaEntity
        val userFormValueIds = jpaQueryFactory
            .select(qUserFormValue.setId)
            .from(qUserFormValue)
            .where(qUserFormValue.setId.`in`(setIds))
            .fetch()

        return setIds.all { it in userFormValueIds }
    }

    override fun saveAll(userFormValueList: List<UserFormValue>): List<UserFormValue> {
        return userFormValueRepository.saveAll(userFormValueList.map { it.toEntity() })
            .map { it.toDomain() }
    }
}