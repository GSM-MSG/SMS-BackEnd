package team.msg.sms.persistence.student.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import team.msg.sms.persistence.student.entity.StudentJpaEntity
import team.msg.sms.persistence.user.entity.UserJpaEntity
import java.util.UUID

@Repository
interface StudentJpaRepository : JpaRepository<StudentJpaEntity, UUID> {
    fun existsByUser(user: UserJpaEntity): Boolean
    fun findByUserId(userId: UUID): StudentJpaEntity
}