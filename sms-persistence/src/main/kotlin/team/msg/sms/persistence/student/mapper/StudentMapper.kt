package team.msg.sms.persistence.student.mapper

import team.msg.sms.domain.student.model.Student
import team.msg.sms.persistence.student.entity.StudentJpaEntity
import team.msg.sms.persistence.user.entity.UserJpaEntity

fun StudentJpaEntity.toDomain() =
    Student(
        id = id,
        stuNum = stuNum,
        department = department,
        contactEmail = contactEmail,
        major = major,
        portfolioUrl = portfolioUrl,
        score = score,
        salary = salary,
        formOfEmployment = formOfEmployment,
        description = description,
        militaryService = militaryService,
        profileImgUrl = profileImgUrl,
        userId = user.id
    )

fun Student.toEntity(
    user: UserJpaEntity
) {
    StudentJpaEntity(
        id = id,
        stuNum = stuNum,
        department = department,
        contactEmail = contactEmail,
        major = major,
        portfolioUrl = portfolioUrl,
        score = score,
        salary = salary,
        formOfEmployment = formOfEmployment,
        description = description,
        militaryService = militaryService,
        profileImgUrl = profileImgUrl,
        user = user
    )
}