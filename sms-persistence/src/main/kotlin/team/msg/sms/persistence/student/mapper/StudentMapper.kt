package team.msg.sms.persistence.student.mapper

import org.springframework.data.repository.findByIdOrNull
import team.msg.sms.domain.student.model.Student
import team.msg.sms.domain.user.exception.UserNotFoundException
import team.msg.sms.persistence.student.entity.StudentJpaEntity
import team.msg.sms.persistence.user.entity.UserJpaEntity
import team.msg.sms.persistence.user.repository.UserJpaRepository
import java.util.UUID

fun StudentJpaEntity.toDomain() =
    Student(
        id = id,
        stuNum = stuNum,
        department = department,
        contactNumber = contactNumber,
        contactEmail = contactEmail,
        major = major,
        portfolioUrl = portfolioUrl,
        workerType = workerType,
        languageCertificate = languageCertificate,
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
        contactNumber = contactNumber,
        contactEmail = contactEmail,
        major = major,
        portfolioUrl = portfolioUrl,
        workerType = workerType,
        languageCertificate = languageCertificate,
        description = description,
        militaryService = militaryService,
        profileImgUrl = profileImgUrl,
        user = user
        )
}