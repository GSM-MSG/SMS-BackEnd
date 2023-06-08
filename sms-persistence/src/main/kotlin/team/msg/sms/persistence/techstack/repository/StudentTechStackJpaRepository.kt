package team.msg.sms.persistence.techstack.repository

import org.springframework.data.repository.CrudRepository
import team.msg.sms.persistence.techstack.entity.StudentTechStackJpaEntity

interface StudentTechStackJpaRepository : CrudRepository<StudentTechStackJpaEntity, Long> {
}