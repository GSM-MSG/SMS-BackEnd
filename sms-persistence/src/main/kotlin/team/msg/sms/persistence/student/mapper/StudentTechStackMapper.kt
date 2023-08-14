package team.msg.sms.persistence.student.mapper

import team.msg.sms.domain.student.model.StudentTechStack
import team.msg.sms.persistence.student.entity.StudentJpaEntity
import team.msg.sms.persistence.student.entity.StudentTechStackJpaEntity
import team.msg.sms.persistence.techstack.entity.TechStackJpaEntity

fun StudentTechStack.toEntity(
    studentJpaEntity: StudentJpaEntity,
    techStackJpaEntity: TechStackJpaEntity
): StudentTechStackJpaEntity =
    StudentTechStackJpaEntity(
        techStack = techStackJpaEntity,
        student = studentJpaEntity
    )

fun StudentTechStackJpaEntity.toDomain(): StudentTechStack =
    StudentTechStack(
        id = this.id,
        studentId = this.student.id,
        techStackId = this.techStack.id
    )