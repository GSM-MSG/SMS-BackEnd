package team.msg.sms.domain.student.dto.request

import team.msg.sms.domain.student.model.FormOfEmployment
import team.msg.sms.domain.student.model.Major
import team.msg.sms.domain.student.model.MilitaryService

data class SignUpData(
    val major: Major,
    val techStack: List<String>,
    val profileImgUrl: String,
    val introduce: String,
    val stuNum: String,
    val portfolioUrl: String,
    val contactEmail: String,
    val formOfEmployment: FormOfEmployment,
    val gsmAuthenticationScore: Int,
    val region: List<String>,
    val salary: Int,
    val languageCertificate: List<String>,
    val dreamBookFileUrl: String,
    val militaryService: MilitaryService,
    val certificate: List<String>
)