package team.msg.sms.persistence.teacher

import org.springframework.stereotype.Component
import team.msg.sms.domain.teacher.model.HomeroomTeacher
import team.msg.sms.domain.teacher.model.Teacher
import team.msg.sms.domain.teacher.spi.HomeroomTeacherPort
import team.msg.sms.domain.user.model.User
import team.msg.sms.persistence.teacher.mapper.toDomain
import team.msg.sms.persistence.teacher.mapper.toEntity
import team.msg.sms.persistence.teacher.repository.HomeroomTeacherJpaRepository
import team.msg.sms.persistence.user.mapper.toEntity

@Component
class HomeroomTeacherPersistenceAdapter(
    private val homeroomTeacherJpaRepository: HomeroomTeacherJpaRepository
) : HomeroomTeacherPort {
    override fun existsHomeroomTeacherByGradeAndClassNum(grade: Int, classNum: Int): Boolean =
        homeroomTeacherJpaRepository.existsByGradeAndClassNum(grade, classNum)

    override fun findHomeroomTeacherByTeacher(teacher: Teacher, user: User): HomeroomTeacher {
        return homeroomTeacherJpaRepository.findByTeacher(teacher.toEntity(user.toEntity())).toDomain()
    }

    override fun saveHomeroomTeacher(homeroomTeacher: HomeroomTeacher, teacher: Teacher, user: User): HomeroomTeacher =
        homeroomTeacherJpaRepository.save(homeroomTeacher.toEntity(teacher.toEntity(user.toEntity()))).toDomain()
}