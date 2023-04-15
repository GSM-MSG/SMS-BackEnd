package team.msg.sms.domain.student.model

import team.msg.sms.common.annotation.Aggregate

@Aggregate
data class Student(
    val id: Long,
    val stuNum: String,
    val department: String,
    val contactNumber: String,
    val contactEmail: String,
    val major: String,
    val portfolioUrl: String?,
    val workerType: WorkerType,
    val languageCertificate: String?,
    val description: String,
    val militaryService: MilitaryService,
    val profileImgUrl: String
)
