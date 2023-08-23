package team.msg.sms.persistence.project.mapper

import team.msg.sms.domain.project.model.Project
import team.msg.sms.persistence.project.entity.ProjectJpaEntity
import team.msg.sms.persistence.student.entity.StudentJpaEntity

fun ProjectJpaEntity.toDomain() =
    Project(
        id = this.id,
        description = this.description,
        title = this.title,
        myActivity = this.myActivity,
        projectIconUrl = this.projectIconUrl,
        startDate = this.startDate,
        endDate = this.endDate,
        studentId = student.id
    )

fun Project.toEntity(
    student: StudentJpaEntity
) =
    ProjectJpaEntity(
        id = id,
        description = this.description,
        title = this.title,
        myActivity = this.myActivity,
        projectIconUrl = this.projectIconUrl,
        startDate = this.startDate,
        endDate = this.endDate,
        student = student
    )