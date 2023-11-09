package team.msg.sms.domain.student.usecase

import org.springframework.transaction.annotation.Transactional
import team.msg.sms.common.annotation.UseCase
import team.msg.sms.domain.student.dto.req.SignUpRequestData
import team.msg.sms.domain.student.exception.StuNumNotRightException
import team.msg.sms.domain.student.exception.StudentNotFoundException
import team.msg.sms.domain.student.model.*
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
    private val techStackService: TechStackService
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
