package team.msg.sms.persistence.teacher.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import team.msg.sms.persistence.teacher.entity.TeacherJpaEntity
import team.msg.sms.persistence.user.entity.UserJpaEntity
import java.util.*

@Repository
interface TeacherJpaRepository : JpaRepository<TeacherJpaEntity, UUID>{
    fun existsByUser(user: UserJpaEntity): Boolean
    fun findByUser(user: UserJpaEntity): TeacherJpaEntity
}