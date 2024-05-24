package team.msg.sms.persistence.teacher.mapper

import team.msg.sms.domain.teacher.model.HomeroomTeacher
import team.msg.sms.persistence.teacher.entity.HomeroomTeacherJpaEntity
import team.msg.sms.persistence.teacher.entity.TeacherJpaEntity

fun HomeroomTeacher.toEntity(teacher: TeacherJpaEntity) =
    HomeroomTeacherJpaEntity(
        id = id,
        grade = grade,
        classNum = classNum,
        teacher = teacher
    )

fun HomeroomTeacherJpaEntity.toDomain() =
    HomeroomTeacher(
        id = id,
        grade = grade,
        classNum = classNum,
        teacherId = teacher.id
    )