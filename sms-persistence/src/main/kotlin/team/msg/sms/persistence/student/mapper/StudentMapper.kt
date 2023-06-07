package team.msg.sms.persistence.student.mapper

import org.springframework.data.domain.Page
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
        gsmAuthenticationScore = gsmAuthenticationScore,
        salary = salary,
        formOfEmployment = formOfEmployment,
        introduce = introduce,
        militaryService = militaryService,
        profileImgUrl = profileImgUrl,
        user = user
    )

fun Page<StudentJpaEntity>.toDomainPageWithUserInfo(): Student.StudentWithPageInfo {
    val studentWithUserInfoList = this.content
        .map {
            Student.StudentWithUserInfo(
                id = it.id,
                major = it.major,
                name = it.user.name,
                techStack = arrayListOf()
            )
        }
    return Student.StudentWithPageInfo(
        students = studentWithUserInfoList,
        page = this.pageable.pageNumber,
        size = this.size,
        last = this.isLast
    )
}