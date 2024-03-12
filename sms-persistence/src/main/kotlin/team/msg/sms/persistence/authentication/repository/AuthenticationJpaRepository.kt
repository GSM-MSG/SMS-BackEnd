package team.msg.sms.persistence.authentication.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import team.msg.sms.domain.student.model.Student
import team.msg.sms.domain.user.model.User
import team.msg.sms.persistence.authentication.entity.AuthenticationJpaEntity
import team.msg.sms.persistence.student.entity.StudentJpaEntity
import java.util.*

@Repository
interface AuthenticationJpaRepository : JpaRepository<AuthenticationJpaEntity, UUID> {
	fun findByStudent(studentJpaEntity: StudentJpaEntity): List<AuthenticationJpaEntity>
}