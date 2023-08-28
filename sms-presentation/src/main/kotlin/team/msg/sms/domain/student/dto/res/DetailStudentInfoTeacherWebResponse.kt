package team.msg.sms.domain.student.dto.res

import team.msg.sms.domain.languagecertificate.model.LanguageCertificate
import team.msg.sms.domain.prize.dto.res.PrizeResponseData
import team.msg.sms.domain.project.dto.res.ProjectResponseData
import team.msg.sms.domain.student.model.Department
import team.msg.sms.domain.student.model.FormOfEmployment
import team.msg.sms.domain.student.model.MilitaryService

data class DetailStudentInfoTeacherWebResponse(
    val name: String,
    val introduce: String,
    val portfolioUrl: String?,
    val grade: Int,
    val classNum: Int,
    val number: Int,
    val department: Department,
    val major: String,
    val profileImgUrl: String,
    @Deprecated(message = "전 Beta Version 사용자들의 접근성을 개방하기 위하여 현재버전에서는 사용하지 않습니다.")
    val profileImg: String,
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