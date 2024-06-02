package team.msg.sms.domain.auth.usecase

import team.msg.sms.common.annotation.UseCase
import team.msg.sms.domain.auth.model.Role
import team.msg.sms.domain.certificate.service.CertificateService
import team.msg.sms.domain.file.service.ImageService
import team.msg.sms.domain.languagecertificate.service.LanguageCertificateService
import team.msg.sms.domain.prize.service.PrizeService
import team.msg.sms.domain.project.service.ProjectLinkService
import team.msg.sms.domain.project.service.ProjectService
import team.msg.sms.domain.project.service.ProjectTechStackService
import team.msg.sms.domain.region.service.RegionService
import team.msg.sms.domain.student.service.StudentService
import team.msg.sms.domain.student.service.StudentTechStackService
import team.msg.sms.domain.user.service.UserService

@UseCase
class WithdrawalUseCase(
    private val userService: UserService,
    private val studentService: StudentService,
    private val projectService: ProjectService,
    private val projectTechStackService: ProjectTechStackService,
    private val regionService: RegionService,
    private val studentTechStackService: StudentTechStackService,
    private val languageCertificateService: LanguageCertificateService,
    private val imageService: ImageService,
    private val certificateService: CertificateService,
    private val projectLinkService: ProjectLinkService,
    private val prizeService: PrizeService
) {
    fun execute() {
        val user = userService.getCurrentUser()
        if (user.roles[0] == Role.ROLE_STUDENT) {
            val student = studentService.getStudentByUser(user)
            val project = projectService.getAllProjectByStudentId(student.id)

            val deleteProjectActions = listOf(
                projectLinkService::deleteAllByProjects,
                projectTechStackService::deleteAllByProjects,
                imageService::deleteAllByProjects
            )

            val deleteStudentActions = listOf(
                projectService::deleteAllByStudent,
                regionService::deleteAllByStudent,
                languageCertificateService::deleteAllByStudent,
                certificateService::deleteAllByStudent,
                studentTechStackService::deleteAllByStudent,
                prizeService::deleteAllByStudent
            )

            deleteActions(deleteProjectActions, project)
            deleteActions(deleteStudentActions, student)

            studentService.deleteByUuid(studentId = student.id)
        }
        userService.deleteByUuid(userId = user.id)
    }
}

fun <T> deleteActions(actions: List<(T) -> Unit>, target: T) {
    actions.forEach { action ->
        action(target)
    }
}

