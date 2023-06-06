package team.msg.sms.domain.student.model

import team.msg.sms.common.annotation.Aggregate
import team.msg.sms.domain.techstack.model.TechStack
import java.util.UUID

@Aggregate
data class Student(
    val id: UUID,
    val department: Department,
    val contactEmail: String,
    val major: Major,
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
        val major: Major,
        val name: String,
        val techStack: List<TechStack>
    )
}
