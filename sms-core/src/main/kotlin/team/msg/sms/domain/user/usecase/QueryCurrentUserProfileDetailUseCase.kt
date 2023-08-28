package team.msg.sms.domain.user.usecase

import team.msg.sms.common.annotation.UseCase
import team.msg.sms.common.util.PrizeUtil.generatePrizeResponseData
import team.msg.sms.common.util.ProjectUtil.generateProjectResponseData
import team.msg.sms.domain.certificate.service.CertificateService
import team.msg.sms.domain.file.service.ImageService
import team.msg.sms.domain.languagecertificate.model.LanguageCertificate
import team.msg.sms.domain.languagecertificate.service.LanguageCertificateService
import team.msg.sms.domain.prize.model.Prize
import team.msg.sms.domain.prize.service.PrizeService
import team.msg.sms.domain.project.model.Project
import team.msg.sms.domain.project.service.ProjectLinkService
import team.msg.sms.domain.project.service.ProjectService
import team.msg.sms.domain.project.service.ProjectTechStackService
import team.msg.sms.domain.region.service.RegionService
import team.msg.sms.domain.student.model.Student
import team.msg.sms.domain.student.model.StudentTechStack
import team.msg.sms.domain.student.service.StudentService
import team.msg.sms.domain.student.service.StudentTechStackService
import team.msg.sms.domain.techstack.model.TechStack
import team.msg.sms.domain.techstack.service.TechStackService
import team.msg.sms.domain.user.dto.res.UserProfileDetailResponseData

@UseCase
class QueryCurrentUserProfileDetailUseCase(
    private val studentService: StudentService,
    private val techStackService: TechStackService,
    private val certificateService: CertificateService,
    private val languageCertificateService: LanguageCertificateService,
    private val projectService: ProjectService,
    private val projectTechStackService: ProjectTechStackService,
    private val projectLinkService: ProjectLinkService,
    private val imageService: ImageService,
    private val studentTechStackService: StudentTechStackService,
    private val regionService: RegionService,
    private val prizeService: PrizeService
) {
    fun execute(): UserProfileDetailResponseData {
        val student = studentService.currentStudent()
        val techStacks = techStackService.getAllTechStack()
        val projects = projectService.getAllProjectByStudentId(studentId = student.id)
        val certificates = certificateService.getCertificateByUuid(student.id)
            .map { it.certificateName }
        val languageCertificates = languageCertificateService.getLanguageCertificateByStudentUuid(student.id)
            .map { it.toLanguageCertificateScore() }
        val region = regionService.getRegionByStudentUuid(student.id)
            .map { it.region }
        val studentTechStacks = studentTechStackService.getStudentTechStackByStudentId(studentId = student.id)
        val prizes = prizeService.getAllPrizeByStudentId(studentId = student.id)
        return toData(
            student,
            techStacks,
            region,
            studentTechStacks,
            languageCertificates,
            certificates,
            projects,
            prizes
        )
    }

    private fun toData(
        student: Student.StudentWithUserInfo,
        techStacks: List<TechStack>,
        regions: List<String>,
        studentTechStacks: List<StudentTechStack>,
        languageCertificates: List<LanguageCertificate.LanguageCertificateScore>,
        certificates: List<String>,
        projects: List<Project>,
        prizes: List<Prize>
    ): UserProfileDetailResponseData =
        UserProfileDetailResponseData(
            name = student.name,
            introduce = student.introduce,
            portfolioUrl = student.portfolioUrl,
            grade = student.stuNum.substring(0, 1).toInt(),
            classNum = student.stuNum.substring(1, 2).toInt(),
            number = student.stuNum.substring(2, 4).toInt(),
            department = student.department,
            major = student.major,
            profileImgUrl = student.profileImgUrl,
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

    private fun LanguageCertificate.toLanguageCertificateScore(
    ): LanguageCertificate.LanguageCertificateScore =
        LanguageCertificate.LanguageCertificateScore(
            languageCertificateName = this.languageCertificateName,
            score = this.score,
        )

    private fun toStudentTechStacks(techStacks: List<TechStack>, studentTechStack: StudentTechStack): TechStack? =
        techStacks.find { it.id == studentTechStack.techStackId }
}