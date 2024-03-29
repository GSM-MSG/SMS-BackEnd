package team.msg.sms.domain.student.usecase

import team.msg.sms.common.annotation.UseCase
import team.msg.sms.common.util.PrizeUtil
import team.msg.sms.common.util.ProjectUtil
import team.msg.sms.domain.file.service.ImageService
import team.msg.sms.domain.prize.service.PrizeService
import team.msg.sms.domain.project.service.ProjectLinkService
import team.msg.sms.domain.project.service.ProjectService
import team.msg.sms.domain.project.service.ProjectTechStackService
import team.msg.sms.domain.student.dto.res.DetailStudentInfoResponseData
import team.msg.sms.domain.student.model.StudentTechStack
import team.msg.sms.domain.student.service.StudentService
import team.msg.sms.domain.student.service.StudentTechStackService
import team.msg.sms.domain.techstack.model.TechStack
import team.msg.sms.domain.techstack.service.TechStackService


@UseCase
class StudentInfoDetailUseCase(
    private val studentService: StudentService,
    private val techStackService: TechStackService,
    private val studentTechStackService: StudentTechStackService,
    private val projectService: ProjectService,
    private val projectTechStackService: ProjectTechStackService,
    private val projectLinkService: ProjectLinkService,
    private val imageService: ImageService,
    private val prizeService: PrizeService
) {
    fun execute(uuid: String): DetailStudentInfoResponseData {
        val student = studentService.getStudentUserInfoByUuid(uuid)
        val projects = projectService.getAllProjectByStudentId(studentId = student.id)
        val techStacks = techStackService.getAllTechStack()
        val studentTechStacks = studentTechStackService.getStudentTechStackByStudentId(studentId = student.id)
        val prizes = prizeService.getAllPrizeByStudentId(studentId = student.id)

        return DetailStudentInfoResponseData(
            name = student.name,
            introduce = student.introduce,
            contactEmail = student.contactEmail,
            grade = student.stuNum.substring(0, 1).toInt(),
            classNum = student.stuNum.substring(1, 2).toInt(),
            number = student.stuNum.substring(2, 4).toInt(),
            department = student.department,
            major = student.major,
            profileImg = student.profileImgUrl,
            techStacks = studentTechStacks.map { studentTechStack ->
                toStudentTechStack(studentTechStack, techStacks)
            },projects = ProjectUtil.generateProjectResponseData(
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

private fun toStudentTechStack(studentTechStack: StudentTechStack, techStack: List<TechStack>) =
    techStack.find { it.id == studentTechStack.techStackId }?.stack ?: ""
