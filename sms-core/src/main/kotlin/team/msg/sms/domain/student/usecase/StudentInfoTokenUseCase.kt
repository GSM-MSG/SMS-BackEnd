package team.msg.sms.domain.student.usecase

import team.msg.sms.common.annotation.UseCase
import team.msg.sms.common.util.PrizeUtil.generatePrizeResponseData
import team.msg.sms.common.util.ProjectUtil.generateProjectResponseData
import team.msg.sms.domain.certificate.service.CertificateService
import team.msg.sms.domain.file.service.ImageService
import team.msg.sms.domain.languagecertificate.model.LanguageCertificate
import team.msg.sms.domain.languagecertificate.service.LanguageCertificateService
import team.msg.sms.domain.prize.service.PrizeService
import team.msg.sms.domain.project.service.ProjectLinkService
import team.msg.sms.domain.project.service.ProjectService
import team.msg.sms.domain.project.service.ProjectTechStackService
import team.msg.sms.domain.region.service.RegionService
import team.msg.sms.domain.student.dto.res.DetailStudentInfoTokenResponseData
import team.msg.sms.domain.student.model.Student
import team.msg.sms.domain.student.model.StudentTechStack
import team.msg.sms.domain.student.service.StudentLinkService
import team.msg.sms.domain.student.service.StudentService
import team.msg.sms.domain.student.service.StudentTechStackService
import team.msg.sms.domain.techstack.model.TechStack
import team.msg.sms.domain.techstack.service.TechStackService

@UseCase
class StudentInfoTokenUseCase(
    private val studentService: StudentService,
    private val certificateService: CertificateService,
    private val studentTechStackService: StudentTechStackService,
    private val projectService: ProjectService,
    private val techStackService: TechStackService,
    private val projectTechStackService: ProjectTechStackService,
    private val projectLinkService: ProjectLinkService,
    private val imageService: ImageService,
    private val languageCertificateService: LanguageCertificateService,
    private val regionService: RegionService,
    private val prizeService: PrizeService,
    private val studentLinkService: StudentLinkService
) {
    fun execute(token: String): DetailStudentInfoTokenResponseData {
        val student: Student.StudentWithUserInfo = studentLinkService.getStudentUserInfoByToken(token)

        val projects = projectService.getAllProjectByStudentId(studentId = student.id)
        val certificates = certificateService.getCertificateByUuid(student.id).map { it.certificateName }
        val languageCertificates =
            languageCertificateService.getLanguageCertificateByStudentUuid(student.id)
                .map { toLanguageCertificateScore(languageCertificate = it) }
        val regions = regionService.getRegionByStudentUuid(student.id).map { it.region }
        val techStacks = techStackService.getAllTechStack()
        val prizes = prizeService.getAllPrizeByStudentId(studentId = student.id)
        val studentTechStacks = studentTechStackService.getStudentTechStackByStudentId(student.id)

        return DetailStudentInfoTokenResponseData(
            name = student.name,
            introduce = student.introduce,
            portfolioUrl = student.portfolioUrl,
            portfolioFileUrl = student.portfolioFileUrl,
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
            techStacks = studentTechStacks.map {
                toStudentTechStacks(techStacks, it)?.stack ?: ""
            },
            projects = generateProjectResponseData(
                projects = projects,
                projectLinkService = projectLinkService,
                projectTechStackService = projectTechStackService,
                imageService = imageService,
                techStacks = techStacks
            ),
            prizes = generatePrizeResponseData(
                prizes = prizes
            )
        )
    }

    private fun toLanguageCertificateScore(
        languageCertificate: LanguageCertificate,
    ): LanguageCertificate.LanguageCertificateScore =
        LanguageCertificate.LanguageCertificateScore(
            languageCertificateName = languageCertificate.languageCertificateName,
            score = languageCertificate.score,
        )

    private fun toStudentTechStacks(techStacks: List<TechStack>, studentTechStack: StudentTechStack): TechStack? =
        techStacks.find { it.id == studentTechStack.techStackId }
}

