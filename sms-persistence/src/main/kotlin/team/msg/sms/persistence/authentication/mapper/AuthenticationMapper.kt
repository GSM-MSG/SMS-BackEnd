package team.msg.sms.persistence.authentication.mapper

import team.msg.sms.domain.authentication.model.Authentication
import team.msg.sms.persistence.authentication.entity.AuthenticationJpaEntity
import team.msg.sms.persistence.student.entity.StudentJpaEntity
import java.time.LocalDateTime

fun Authentication.toEntity(student: StudentJpaEntity) = AuthenticationJpaEntity(
    id = id,
    title = title,
    content = content,
    activityImages = activityImages,
    score = score,
    activityStatus = activityStatus,
    student = student,
)

fun AuthenticationJpaEntity.toDomain() = Authentication(
    id = id,
    title = title,
    content = content,
    activityImages = activityImages,
    score = score,
    activityStatus = activityStatus,
    studentId = student.id
)

fun AuthenticationJpaEntity.toDomainWithUserInfoAndRequestedTime(requestedTime: LocalDateTime) = Authentication.AuthenticationWithStudentInfoAndRequestedTime(
    id = id,
    title = title,
    requestedTime = requestedTime,
    stuNum = student.user.stuNum,
    name = student.user.name,
    department = student.department
)