package team.msg.sms.persistence.student.repository

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import team.msg.sms.persistence.student.entity.StudentLinkEntity

@Repository
interface StudentLinkRepository : CrudRepository<StudentLinkEntity, Long> {
	fun existsByToken(token: String): Boolean
	fun findByToken(token: String): StudentLinkEntity?
}
