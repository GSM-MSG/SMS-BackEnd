package team.msg.sms.persistence.student.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Lock
import org.springframework.stereotype.Repository
import team.msg.sms.persistence.student.entity.StudentJpaEntity
import team.msg.sms.persistence.user.entity.UserJpaEntity
import java.util.UUID
import javax.persistence.LockModeType

@Repository
interface StudentJpaRepository : JpaRepository<StudentJpaEntity, UUID> {
    fun existsByUser(user: UserJpaEntity): Boolean
    fun findByUserId(userId: UUID): StudentJpaEntity

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    fun findByUser(user: UserJpaEntity): StudentJpaEntity
}