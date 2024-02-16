package team.msg.sms.persistence.teacher.repository

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import team.msg.sms.persistence.teacher.entity.HomeroomTeacherJpaEntity

@Repository
interface HomeroomTeacherJpaRepository : CrudRepository<HomeroomTeacherJpaEntity, Long> {
    fun existsByGradeAndClassNum(grade: Int, classNum: Int): Boolean
}