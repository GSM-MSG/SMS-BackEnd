package team.msg.sms.domain.user.dto.res

import team.msg.sms.domain.languagecertificate.model.LanguageCertificate
import team.msg.sms.domain.prize.dto.res.PrizeResponseData
import team.msg.sms.domain.project.dto.res.ProjectResponseData
import team.msg.sms.domain.student.model.Department
import team.msg.sms.domain.student.model.FormOfEmployment
import team.msg.sms.domain.student.model.MilitaryService

data class UserProfileDetailResponseData(
    val name: String,
    val introduce: String,
    val portfolioUrl: String,
    val portfolioFileUrl: String?,
    val grade: Int,
    val classNum: Int,
    val number: Int,
    val department: Department,
    val major: String,
    val profileImgUrl: String,
    val contactEmail: String,
    val gsmAuthenticationScore: Int,
    val formOfEmployment: FormOfEmployment,
    val regions: List<String>, // 근무지역
    val militaryService: MilitaryService,
    val salary: Int,
    val languageCertificates: List<LanguageCertificate.LanguageCertificateScore>,
    val certificates: List<String>,
    val techStacks: List<String>,
    val projects: List<ProjectResponseData>,
    val prizes: List<PrizeResponseData>
)