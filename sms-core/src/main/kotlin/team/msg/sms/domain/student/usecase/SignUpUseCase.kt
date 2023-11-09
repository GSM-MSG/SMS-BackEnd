package team.msg.sms.domain.student.usecase

import org.springframework.transaction.annotation.Transactional
import team.msg.sms.common.annotation.UseCase
import team.msg.sms.common.util.ProjectUtil
import team.msg.sms.common.util.ProjectUtil.validatePreviewImageLimit
import team.msg.sms.domain.certificate.model.Certificate
import team.msg.sms.domain.certificate.service.CertificateService
import team.msg.sms.domain.file.model.Image
import team.msg.sms.domain.file.service.ImageService
import team.msg.sms.domain.languagecertificate.dto.req.LanguageCertificateRequestData
import team.msg.sms.domain.languagecertificate.model.LanguageCertificate
import team.msg.sms.domain.languagecertificate.service.LanguageCertificateService
import team.msg.sms.domain.prize.dto.req.PrizeRequestData
import team.msg.sms.domain.prize.model.Prize
import team.msg.sms.domain.prize.service.PrizeService
import team.msg.sms.domain.project.dto.req.LinkRequestData
import team.msg.sms.domain.project.dto.req.ProjectRequestData
import team.msg.sms.domain.project.model.Project
import team.msg.sms.domain.project.model.ProjectLink
import team.msg.sms.domain.project.model.ProjectTechStack
import team.msg.sms.domain.project.service.ProjectLinkService
import team.msg.sms.domain.project.service.ProjectService
import team.msg.sms.domain.project.service.ProjectTechStackService
import team.msg.sms.domain.region.model.Region
import team.msg.sms.domain.region.service.RegionService
import team.msg.sms.domain.student.dto.req.SignUpRequestData
import team.msg.sms.domain.student.exception.StuNumNotRightException
import team.msg.sms.domain.student.exception.StudentNotFoundException
import team.msg.sms.domain.student.model.Department
import team.msg.sms.domain.student.model.Student
import team.msg.sms.domain.student.model.StudentTechStack
import team.msg.sms.domain.student.service.StudentService
import team.msg.sms.domain.student.service.StudentTechStackService
import team.msg.sms.domain.techstack.model.TechStack
import team.msg.sms.domain.techstack.service.TechStackService
import team.msg.sms.domain.user.model.User
import team.msg.sms.domain.user.service.UserService
import java.util.*

@UseCase
class SignUpUseCase(
    private val studentService: StudentService,
    private val studentTechStackService: StudentTechStackService,
    private val userService: UserService,
    private val techStackService: TechStackService,
    private val regionService: RegionService,
    private val languageCertificateService: LanguageCertificateService,
    private val certificateService: CertificateService,
    private val projectService: ProjectService,
    private val projectTechStackService: ProjectTechStackService,
    private val projectLinkService: ProjectLinkService,
    private val imageService: ImageService,
    private val prizeService: PrizeService
) {
    @Transactional(rollbackFor = [Exception::class])
    fun execute(signUpData: SignUpRequestData) {
        val user = userService.getCurrentUser()

        studentService.checkStudentExistsByUser(user)

        val signUpStudent = toStudentModel(signUpData, user)

        val student = studentService.saveStudent(signUpStudent, user)

        val techStacks = techStackService.getAllTechStack().toMutableList()

        studentTechStackValid(
            studentId = student.id,
            stack = techStacks,
            studentTechStacks = signUpData.techStacks
        )
    }

    private fun toStackModel(stack: String): TechStack =
        TechStack(
            id = 0,
            stack = stack,
            count = 0
        )

    private fun toStudentModel(signUpData: SignUpRequestData, user: User): Student =
        Student(
            id = UUID.randomUUID(),
            department = findDepartment(user.stuNum),
            contactEmail = signUpData.contactEmail,
            major = signUpData.major,
            portfolioUrl = signUpData.portfolioUrl,
            gsmAuthenticationScore = signUpData.gsmAuthenticationScore,
            salary = signUpData.salary,
            formOfEmployment = signUpData.formOfEmployment,
            introduce = signUpData.introduce,
            militaryService = signUpData.militaryService,
            profileImgUrl = signUpData.profileImgUrl,
            userId = user.id
        )

    private fun toStudentTechStackModel(studentId: UUID, techStackId: Long): StudentTechStack =
        StudentTechStack(
            id = 0,
            studentId = studentId,
            techStackId = techStackId
        )

    private fun studentTechStackValid(
        stack: MutableList<TechStack>,
        studentTechStacks: List<String>,
        studentId: UUID
    ) {
        for (stackItem in studentTechStacks) {
            val techStackData = stack.find { it.stack == stackItem }
            if (techStackData == null) {
                val techStack = techStackService.save(toStackModel(stackItem))
                stack.add(0, techStack)
                studentTechStackService.save(toStudentTechStackModel(studentId, techStack.id))
            } else {
                val techStack =
                    techStackService.save(techStack = techStackData.copy(count = techStackData.count + 1))
                stack.add(0, techStack)
                studentTechStackService.save(toStudentTechStackModel(studentId, techStack.id))
            }
        }
    }

    private fun findDepartment(stuNum: String): Department {
        if (stuNum.isEmpty())
            throw StudentNotFoundException
        val departmentCode = stuNum.slice(IntRange(1, 1))
        return when {
            stuNum.startsWith("1") -> when (departmentCode) {
                "1", "2" -> Department.SW_DEVELOPMENT
                "3" -> Department.SMART_IOT_DEVELOPMENT
                "4" -> Department.AI_DEVELOPMENT
                else -> throw StuNumNotRightException
            }

            else -> when (departmentCode) {
                "1", "2" -> Department.SW_DEVELOPMENT
                "3", "4" -> Department.SMART_IOT_DEVELOPMENT
                else -> throw StuNumNotRightException
            }
        }
    }
}
