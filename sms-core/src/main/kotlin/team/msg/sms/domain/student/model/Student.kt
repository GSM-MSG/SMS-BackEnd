package team.msg.sms.domain.student.model

import team.msg.sms.common.annotation.Aggregate
import java.util.UUID

@Aggregate
data class Student(
    val id: UUID,
    val stuNum: String,
    val department: String,
    val contactEmail: String,
    val major: Major,
    val portfolioUrl: String?,
    val score: Number,
    val salary: Number,
    val formOfEmployment: FormOfEmployment,
    val description: String,
    val militaryService: MilitaryService,
    val profileImgUrl: String,
    val userId: UUID
)
