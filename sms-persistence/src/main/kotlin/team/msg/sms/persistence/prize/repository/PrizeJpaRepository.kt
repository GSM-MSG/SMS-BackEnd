package team.msg.sms.persistence.prize.repository

import org.springframework.data.repository.CrudRepository
import team.msg.sms.persistence.prize.entity.PrizeJpaEntity
import team.msg.sms.persistence.student.entity.StudentJpaEntity

interface PrizeJpaRepository : CrudRepository<PrizeJpaEntity, Long> {
    fun findAllByStudent(studentJpaEntity: StudentJpaEntity): List<PrizeJpaEntity>
    fun deleteAllByStudent(studentJpaEntity: StudentJpaEntity)
}