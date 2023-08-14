package team.msg.sms.persistence.student.repository

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import team.msg.sms.persistence.student.entity.StudentJpaEntity
import team.msg.sms.persistence.student.entity.StudentTechStackJpaEntity
import java.util.UUID

@Repository
interface StudentTechStackJpaRepository : CrudRepository<StudentTechStackJpaEntity, Long> {
    fun findAllByStudentId(studentId: UUID): List<StudentTechStackJpaEntity>
    fun deleteAllByStudent(studentJpaEntity: StudentJpaEntity)
}