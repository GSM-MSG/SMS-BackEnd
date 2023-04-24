package team.msg.sms.domain.student.model

import team.msg.sms.common.annotation.Aggregate
import java.util.UUID

@Aggregate
data class Student(
    val id: UUID,
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
    val profileImgUrl: String,
    val userId: UUID
) {
}
