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
        portfolioFileUrl = portfolioFileUrl,
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
        portfolioFileUrl = portfolioFileUrl,
        salary = salary,
        name = user.name,
        profileImgUrl = profileImgUrl,
        techStack = arrayListOf(),
        userId = user.id
    )

fun Page<StudentJpaEntity>.toDomainPageWithUserInfo(): Student.StudentWithPageInfo {
    val studentWithUserInfoList = this.content
        .map {
            it.toDomainWithUserInfo()
        }
    return Student.StudentWithPageInfo(
        students = studentWithUserInfoList,
        page = this.pageable.pageNumber + 1,
        contentSize = this.content.size,
        totalSize = this.totalElements,
        last = this.isLast
    )
}

fun Student.StudentWithUserInfo.toEntity(user: UserJpaEntity): StudentJpaEntity =
    StudentJpaEntity(
        id = id,
        department = department,
        contactEmail = contactEmail,
        major = major,
        portfolioUrl = portfolioUrl,
        portfolioFileUrl = portfolioFileUrl,
        gsmAuthenticationScore = gsmAuthenticationScore,
        salary = salary,
        formOfEmployment = formOfEmployment,
        introduce = introduce,
        militaryService = militaryService,
        profileImgUrl = profileImgUrl,
        user = user
    )