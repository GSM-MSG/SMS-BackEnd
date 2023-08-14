package team.msg.sms.domain.user.usecase

import team.msg.sms.common.annotation.UseCase
import team.msg.sms.domain.certificate.service.CertificateService
import team.msg.sms.domain.file.model.Image
import team.msg.sms.domain.file.service.ImageService
import team.msg.sms.domain.languagecertificate.model.LanguageCertificate
import team.msg.sms.domain.languagecertificate.service.LanguageCertificateService
import team.msg.sms.domain.project.dto.res.ProjectInProgressResponseData
import team.msg.sms.domain.project.dto.res.ProjectLinkResponseData
import team.msg.sms.domain.project.dto.res.ProjectResponseData
import team.msg.sms.domain.project.model.Project
import team.msg.sms.domain.project.model.ProjectLink
import team.msg.sms.domain.project.model.ProjectTechStack
import team.msg.sms.domain.project.service.ProjectLinkService
import team.msg.sms.domain.project.service.ProjectService
import team.msg.sms.domain.project.service.ProjectTechStackService
import team.msg.sms.domain.region.service.RegionService
import team.msg.sms.domain.student.exception.StudentNotFoundException
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
    private val regionService: RegionService
) {
    fun execute(): UserProfileDetailResponseData {
        val student = studentService.currentStudent()
        val techStacks = techStackService.getAllTechStack()
        val projects = projectService.getAllProjectByStudentId(studentId = student.id)
        val certificates = certificateService.getCertificateByUuid(student.id).map { it.certificateName }
        val languageCertificates = languageCertificateService.getLanguageCertificateByStudentUuid(student.id)
            .map { it.toLanguageCertificateScore() }
        val region = regionService.getRegionByStudentUuid(student.id).map { it.region }
        val studentTechStacks = studentTechStackService.getStudentTechStackByStudentId(studentId = student.id)
        return toData(student, techStacks, region, studentTechStacks, languageCertificates, certificates, projects)
    }

    private fun toData(
        student: Student.StudentWithUserInfo,
        techStacks: List<TechStack>,
        regions: List<String>,
        studentTechStacks: List<StudentTechStack>,
        languageCertificates: List<LanguageCertificate.LanguageCertificateScore>,
        certificates: List<String>,
        projects: List<Project>
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
            studentTechStacks = studentTechStacks.map {
                toStudentTechStacks(techStacks, it)?.stack ?: ""
            },
            projects = projects.map {
                val image = imageService.getAllByProjectId(projectId = it.id)
                val link = projectLinkService.getAllByProjectId(projectId = it.id)
                val projectTechStack = projectTechStackService.getAllByProjectId(projectId = it.id)
                toProjectResponseData(
                    project = it,
                    projectLink = link,
                    projectImage = image,
                    projectTechStack = projectTechStack,
                    techStack = techStacks
                )
            }
        )

    private fun LanguageCertificate.toLanguageCertificateScore(
    ): LanguageCertificate.LanguageCertificateScore =
        LanguageCertificate.LanguageCertificateScore(
            languageCertificateName = this.languageCertificateName,
            score = this.score,
        )

    private fun toStudentTechStacks(techStacks: List<TechStack>, studentTechStack: StudentTechStack): TechStack? =
        techStacks.find { it.id == studentTechStack.techStackId }

    private fun toProjectTechStacks(techStacks: List<TechStack>, projectTechStack: ProjectTechStack): TechStack? =
        techStacks.find { it.id == projectTechStack.techStackId }

    private fun toProjectResponseData(
        project: Project,
        projectLink: List<ProjectLink>,
        projectImage: List<Image>,
        projectTechStack: List<ProjectTechStack>,
        techStack: List<TechStack>
    ): ProjectResponseData =
        ProjectResponseData(
            id = project.id,
            description = project.description,
            inProgress = toInProgressResponseData(project.startDate, project.endDate),
            links = projectLink.map { toLinkResponseData(it) },
            myActivity = project.myActivity,
            previewImages = projectImage.map { it.imageUrl },
            projectTechStacks = projectTechStack.map {
                toProjectTechStacks(techStack, it)?.stack ?: throw StudentNotFoundException
            },
            name = project.title,
        )


    private fun toLinkResponseData(projectLink: ProjectLink) =
        ProjectLinkResponseData(
            id = projectLink.projectId,
            name = projectLink.name,
            url = projectLink.url
        )

    private fun toInProgressResponseData(start: String, end: String?) =
        ProjectInProgressResponseData(
            start = start,
            end = end
        )
}