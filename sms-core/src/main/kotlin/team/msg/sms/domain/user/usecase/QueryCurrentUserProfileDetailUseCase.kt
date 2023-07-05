package team.msg.sms.domain.user.usecase

import team.msg.sms.common.annotation.UseCase
import team.msg.sms.domain.certificate.service.CertificateService
import team.msg.sms.domain.languagecertificate.model.LanguageCertificate
import team.msg.sms.domain.languagecertificate.service.LanguageCertificateService
import team.msg.sms.domain.region.service.RegionService
import team.msg.sms.domain.student.model.Student
import team.msg.sms.domain.student.service.StudentService
import team.msg.sms.domain.techstack.service.TechStackService
import team.msg.sms.domain.user.dto.res.UserProfileDetailResponseData

@UseCase
class QueryCurrentUserProfileDetailUseCase(
    private val studentService: StudentService,
    private val techStackService: TechStackService,
    private val certificateService: CertificateService,
    private val languageCertificateService: LanguageCertificateService,
    private val regionService: RegionService
) {
    fun execute(): UserProfileDetailResponseData {
        val student = studentService.currentStudent()
        val techStacks = techStackService.getTechStackByStudentUuid(student.id).map { it.stack }
        val certificates = certificateService.getCertificateByUuid(student.id).map { it.certificateName }
        val languageCertificates= languageCertificateService.getLanguageCertificateByStudentUuid(student.id)
            .map { it.toLanguageCertificateScore() }
        val region = regionService.getRegionByStudentUuid(student.id).map { it.region }
        return toData(student, techStacks, region, languageCertificates, certificates)
    }

    private fun toData(
        student: Student.StudentWithUserInfo,
        techStacks: List<String>,
        regions: List<String>,
        languageCertificates: List<LanguageCertificate.LanguageCertificateScore>,
        certificates: List<String>
    ): UserProfileDetailResponseData =
        UserProfileDetailResponseData(
            name = student.name,
            introduce = student.introduce,
            dreamBookFileUrl = student.dreamBookFileUrl,
            portfolioUrl = student.portfolioUrl,
            grade = student.stuNum.substring(0, 1).toInt(),
            classNum = student.stuNum.substring(1, 2).toInt(),
            number = student.stuNum.substring(2, 4).toInt(),
            department = student.department,
            major = student.major,
            profileImg = student.profileImgUrl,
            contactEmail = student.contactEmail,
            gsmAuthenticationScore = student.gsmAuthenticationScore,
            formOfEmployment = student.formOfEmployment,
            regions = regions,
            militaryService = student.militaryService,
            salary = student.salary,
            languageCertificates = languageCertificates,
            certificates = certificates,
            techStacks = techStacks
        )

    private fun LanguageCertificate.toLanguageCertificateScore(
    ): LanguageCertificate.LanguageCertificateScore =
        LanguageCertificate.LanguageCertificateScore(
            languageCertificateName = this.languageCertificateName,
            score = this.score,
        )
}