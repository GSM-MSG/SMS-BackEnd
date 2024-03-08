package team.msg.sms.persistence.teacher

import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.stereotype.Component
import team.msg.sms.domain.teacher.model.HomeroomTeacher
import team.msg.sms.domain.teacher.model.Teacher
import team.msg.sms.domain.teacher.spi.HomeroomTeacherPort
import team.msg.sms.domain.user.model.User
import team.msg.sms.persistence.teacher.entity.QTeacherJpaEntity.teacherJpaEntity as teacher
import team.msg.sms.persistence.teacher.entity.QHomeroomTeacherJpaEntity.homeroomTeacherJpaEntity as homeroomTeacher
import team.msg.sms.persistence.user.entity.QUserJpaEntity.userJpaEntity as user
import team.msg.sms.persistence.teacher.mapper.toDomain
import team.msg.sms.persistence.teacher.mapper.toEntity
import team.msg.sms.persistence.teacher.repository.HomeroomTeacherJpaRepository
import team.msg.sms.persistence.user.mapper.toEntity
import java.util.UUID

@Component
class HomeroomTeacherPersistxenceAdapter(
    private val homeroomTeacherJpaRepository: HomeroomTeacherJpaRepository,
    private val queryFactory: JPAQueryFactory
) : HomeroomTeacherPort {
    override fun existsHomeroomTeacherByGradeAndClassNum(grade: Int, classNum: Int): Boolean =
        homeroomTeacherJpaRepository.existsByGradeAndClassNum(grade, classNum)

    override fun queryHomeroomTeacherByUserId(userId: UUID): HomeroomTeacher? =
        queryFactory.selectFrom(homeroomTeacher)
            .innerJoin(homeroomTeacher.teacher, teacher)
            .innerJoin(teacher.user, user)
            .where(user.id.eq(userId))
            .fetchOne()?.toDomain()

    override fun saveHomeroomTeacher(homeroomTeacher: HomeroomTeacher, teacher: Teacher, user: User): HomeroomTeacher =
        homeroomTeacherJpaRepository.save(homeroomTeacher.toEntity(teacher.toEntity(user.toEntity()))).toDomain()
}