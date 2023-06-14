package team.msg.sms.domain.student.dto.req

import team.msg.sms.domain.languagecertificate.dto.req.LanguageCertificateRequest
import team.msg.sms.domain.student.dto.request.SignUpData
import team.msg.sms.domain.student.model.FormOfEmployment
import team.msg.sms.domain.student.model.MilitaryService
import javax.validation.constraints.*

data class SignUpWebRequest(
    val major: String,

    val techStack: List<String>,

    @field:NotBlank
    @field:Pattern(regexp = "^https://.*")
    val profileImgUrl: String,

    @field:Size(max = 50)
    val introduce: String,

    @field:NotBlank
    @field:Pattern(regexp = "^(https?://).*")
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
            portfolioUrl = portfolioUrl,
            contactEmail = contactEmail,
            formOfEmployment = formOfEmployment,
            gsmAuthenticationScore = gsmAuthenticationScore,
            region = region,
            salary = salary,
            languageCertificate = languageCertificate.map { it.toData() },
            dreamBookFileUrl = dreamBookFileUrl,
            militaryService = militaryService,
            certificate = certificate
        )
}
