package team.msg.sms.domain.student.model

import team.msg.sms.common.annotation.Aggregate
import team.msg.sms.domain.major.model.Major
import java.util.UUID

@Aggregate
data class Student(
    val id: UUID,
    val department: Department,
    val contactEmail: String,
    val major: String,
    val portfolioUrl: String?,
    val gsmAuthenticationScore: Int,
    val salary: Int,
    val formOfEmployment: FormOfEmployment,
    val introduce: String,
    val militaryService: MilitaryService,
    val profileImgUrl: String,
    val userId: UUID
) {
    data class StudentWithUserInfo(
        val id: UUID,
        val major: String,
        val name: String,
        val profileImgUrl: String,
        val techStack: List<String>
    )

    data class StudentWithPageInfo(
        val students: List<StudentWithUserInfo>,
        val page: Int,
        val contentSize: Int,
        val totalSize: Long,
        val last: Boolean
    )
}
