package team.msg.sms.persistence.prize.repository

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import team.msg.sms.persistence.prize.entity.PrizeJpaEntity
import team.msg.sms.persistence.student.entity.StudentJpaEntity

@Repository
interface PrizeJpaRepository : CrudRepository<PrizeJpaEntity, Long> {
    fun findAllByStudent(studentJpaEntity: StudentJpaEntity): List<PrizeJpaEntity>
    fun deleteAllByStudent(studentJpaEntity: StudentJpaEntity)
}