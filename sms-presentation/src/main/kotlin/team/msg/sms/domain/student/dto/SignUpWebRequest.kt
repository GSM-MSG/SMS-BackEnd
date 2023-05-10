package team.msg.sms.domain.student.dto

import team.msg.sms.domain.languagecertificate.dto.request.LanguageCertificateRequest
import team.msg.sms.domain.languagecertificate.model.LanguageCertificate
import team.msg.sms.domain.student.dto.request.SignUpData
import team.msg.sms.domain.student.model.FormOfEmployment
import team.msg.sms.domain.student.model.Major
import team.msg.sms.domain.student.model.MilitaryService
import javax.validation.constraints.*

data class SignUpWebRequest(
    val major: Major,

    val techStack: List<String>,

    @field:NotBlank
    @field:Pattern(regexp = "^https://.*")
    val profileImgUrl: String,

    @field:Size(max = 255)
    val introduce: String,

    @field:NotBlank
    @field:Size(min = 1, max = 4)
    val stuNum: String,

    @field:NotBlank
    @field:Pattern(regexp = "^https://.*")
    val portfolioUrl: String,

    @field:NotBlank
    @field:Email
    val contactEmail: String,

    val region: List<String>,

    val formOfEmployment: FormOfEmployment,

    @field:NotNull
    val gsmAuthenticationScore: Int,

    @field:NotNull
    val salary: Int,

    val languageCertificate: List<LanguageCertificateRequest>,

    @field:NotBlank
    @field:Pattern(regexp = "^https://.*")
    val dreamBookFileUrl: String,

    val militaryService: MilitaryService,

    val certificate: List<String>
) {
    fun toData(): SignUpData =
        SignUpData(
            major = major,
            techStack = techStack,
            profileImgUrl = profileImgUrl,
            introduce = introduce,
            stuNum = stuNum,
            portfolioUrl = portfolioUrl,
            contactEmail = contactEmail,
            formOfEmployment = formOfEmployment,
            gsmAuthenticationScore = gsmAuthenticationScore,
            region = region,
            salary = salary,
            languageCertificate = languageCertificate,
            dreamBookFileUrl = dreamBookFileUrl,
            militaryService = militaryService,
            certificate = certificate
        )
}
