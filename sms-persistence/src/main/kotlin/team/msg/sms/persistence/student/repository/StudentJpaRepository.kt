package team.msg.sms.persistence.student.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import team.msg.sms.domain.student.model.Student
import team.msg.sms.persistence.student.entity.StudentJpaEntity
import java.util.UUID

@Repository
interface StudentJpaRepository : JpaRepository<StudentJpaEntity, UUID> {
}