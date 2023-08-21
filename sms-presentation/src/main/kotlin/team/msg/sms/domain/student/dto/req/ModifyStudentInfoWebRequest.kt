package team.msg.sms.domain.student.dto.req

import team.msg.sms.domain.languagecertificate.dto.req.LanguageCertificateWebRequest
import team.msg.sms.domain.project.dto.req.ProjectRequestData
import team.msg.sms.domain.student.model.FormOfEmployment
import team.msg.sms.domain.student.model.MilitaryService
import javax.validation.constraints.*

data class ModifyStudentInfoWebRequest(
    val major: String,

    val studentTechStacks: List<String>,

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

    val languageCertificate: List<LanguageCertificateWebRequest>,

    val militaryService: MilitaryService,

    val certificate: List<String>,

    val projects: List<ProjectRequestData>
) {
    fun toData(): ModifyStudentInfoRequestData =
        ModifyStudentInfoRequestData(
            major = major,
            studentTechStacks = studentTechStacks,
            profileImgUrl = profileImgUrl,
            introduce = introduce,
            portfolioUrl = portfolioUrl,
            contactEmail = contactEmail,
            formOfEmployment = formOfEmployment,
            gsmAuthenticationScore = gsmAuthenticationScore,
            region = region,
            salary = salary,
            languageCertificate = languageCertificate.map { it.toData() },
            militaryService = militaryService,
            certificate = certificate,
            projects = projects
        )
}