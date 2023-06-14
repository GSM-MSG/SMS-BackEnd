package team.msg.sms.domain.student.dto.request

import team.msg.sms.domain.languagecertificate.dto.LanguageCertificateData
import team.msg.sms.domain.student.model.FormOfEmployment
import team.msg.sms.domain.student.model.MilitaryService

data class SignUpData(
    val major: String,
    val techStack: List<String>,
    val profileImgUrl: String,
    val introduce: String,
    val portfolioUrl: String,
    val contactEmail: String,
    val formOfEmployment: FormOfEmployment,
    val gsmAuthenticationScore: Int,
    val region: List<String>,
    val salary: Int,
    val languageCertificate: List<LanguageCertificateData>,
    val dreamBookFileUrl: String,
    val militaryService: MilitaryService,
    val certificate: List<String>
)