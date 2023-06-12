package team.msg.sms.persistence.techstack.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import team.msg.sms.persistence.student.entity.StudentJpaEntity
import team.msg.sms.persistence.techstack.entity.TechStackJpaEntity
import java.util.UUID

@Repository
interface TechStackJpaRepository : JpaRepository<TechStackJpaEntity, Long> {
    fun findByStudentId(uuid: UUID): List<TechStackJpaEntity>
    fun findByStackStartingWith(stack: String): List<TechStackJpaEntity>
    fun deleteAllByStudent(student: StudentJpaEntity)
}