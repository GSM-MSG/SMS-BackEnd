package team.msg.sms.persistence.student.mapper

import team.msg.sms.domain.student.model.Student
import team.msg.sms.persistence.student.entity.StudentJpaEntity
import team.msg.sms.persistence.user.entity.UserJpaEntity

fun StudentJpaEntity.toDomain() =
    Student(
        id = id,
        department = department,
        contactEmail = contactEmail,
        major = major,
        portfolioUrl = portfolioUrl,
        dreamBookFileUrl = dreamBookFileUrl,
        gsmAuthenticationScore = gsmAuthenticationScore,
        salary = salary,
        formOfEmployment = formOfEmployment,
        introduce = introduce,
        militaryService = militaryService,
        profileImgUrl = profileImgUrl,
        userId = user.id
    )

fun Student.toEntity(
    user: UserJpaEntity
) =
    StudentJpaEntity(
        id = id,
        department = department,
        contactEmail = contactEmail,
        major = major,
        portfolioUrl = portfolioUrl,
        dreamBookFileUrl = dreamBookFileUrl,
        gsmAuthenticationScore = gsmAuthenticationScore,
        salary = salary,
        formOfEmployment = formOfEmployment,
        introduce = introduce,
        militaryService = militaryService,
        profileImgUrl = profileImgUrl,
        user = user
    )

fun StudentJpaEntity.toDomainWithUserInfo(): Student.StudentWithUserInfo =
    Student.StudentWithUserInfo(
        id = id,
        major = major,
        stuNum = user.stuNum,
        introduce = introduce,
        department = department,
        contactEmail = contactEmail,
        formOfEmployment = formOfEmployment,
        gsmAuthenticationScore = gsmAuthenticationScore,
        militaryService = militaryService,
        portfolioUrl = portfolioUrl,
        dreamBookFileUrl = dreamBookFileUrl,
        salary = salary,
        name = user.name,
        profileImgUrl = profileImgUrl,
        techStack = arrayListOf()
    )