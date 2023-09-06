package team.msg.sms.domain.student.usecase

import team.msg.sms.common.annotation.UseCase
import team.msg.sms.common.util.PrizeUtil
import team.msg.sms.common.util.ProjectUtil
import team.msg.sms.domain.file.service.ImageService
import team.msg.sms.domain.prize.service.PrizeService
import team.msg.sms.domain.project.service.ProjectLinkService
import team.msg.sms.domain.project.service.ProjectService
import team.msg.sms.domain.project.service.ProjectTechStackService
import team.msg.sms.domain.student.dto.res.DetailStudentInfoAnonymousResponseData
import team.msg.sms.domain.student.service.StudentService
import team.msg.sms.domain.student.service.StudentTechStackService
import team.msg.sms.domain.techstack.service.TechStackService

@UseCase
class StudentInfoAnonymousUseCase(
    private val studentService: StudentService,
    private val techStackService: TechStackService,
    private val studentTechStackService: StudentTechStackService,
    private val projectService: ProjectService,
    private val projectTechStackService: ProjectTechStackService,
    private val projectLinkService: ProjectLinkService,
    private val imageService: ImageService,
    private val prizeService: PrizeService
) {
    fun execute(uuid: String): DetailStudentInfoAnonymousResponseData {
        val student = studentService.getStudentUserInfoByUuid(uuid)
        val techStacks = techStackService.getAllTechStack()
        val projects = projectService.getAllProjectByStudentId(studentId = student.id)
        val studentTechStacks = studentTechStackService.getStudentTechStackByStudentId(studentId = student.id)
        val prizes = prizeService.getAllPrizeByStudentId(studentId = student.id)

        return DetailStudentInfoAnonymousResponseData(
            name = student.name.replaceRange(1 until student.name.length, "*".repeat(2)),
            introduce = student.introduce,
            major = student.major,
            profileImg = "",
            contactEmail = student.contactEmail,
            techStacks = studentTechStacks.map { studentTechStack ->
                techStacks.find { it.id == studentTechStack.techStackId }?.stack ?: ""
            },
            projects = ProjectUtil.generateProjectResponseData(
                projects = projects,
                projectLinkService = projectLinkService,
                projectTechStackService = projectTechStackService,
                imageService = imageService,
                techStacks = techStacks
            ),
            prizes = PrizeUtil.generatePrizeResponseData(
                prizes = prizes
            )
        )
    }
}