package team.msg.sms.domain.student.dto.req

import team.msg.sms.domain.languagecertificate.dto.req.LanguageCertificateRequestData
import team.msg.sms.domain.prize.dto.req.PrizeRequestData
import team.msg.sms.domain.project.dto.req.ProjectRequestData
import team.msg.sms.domain.student.model.FormOfEmployment
import team.msg.sms.domain.student.model.MilitaryService

data class SignUpRequestData(
    val major: String,
    val studentTechStacks: List<String>,
    val profileImgUrl: String,
    val introduce: String,
    val portfolioUrl: String,
    val contactEmail: String,
    val formOfEmployment: FormOfEmployment,
    val gsmAuthenticationScore: Int,
    val region: List<String>,
    val salary: Int,
    val languageCertificate: List<LanguageCertificateRequestData>,
    val militaryService: MilitaryService,
    val certificate: List<String>,
    val projects: List<ProjectRequestData>,
    val prizes: List<PrizeRequestData>
)