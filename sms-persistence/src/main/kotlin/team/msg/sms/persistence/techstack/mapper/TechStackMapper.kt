package team.msg.sms.persistence.techstack.mapper

import team.msg.sms.domain.techstack.model.TechStack
import team.msg.sms.persistence.student.entity.StudentJpaEntity
import team.msg.sms.persistence.techstack.entity.TechStackJpaEntity

fun TechStackJpaEntity.toDomain() =
    TechStack(
        id = id,
        stack = stack,
        studentId = student.id
    )
fun TechStack.toEntity(
    student: StudentJpaEntity
) =
    TechStackJpaEntity(
        stack = stack,
        student = student
    )