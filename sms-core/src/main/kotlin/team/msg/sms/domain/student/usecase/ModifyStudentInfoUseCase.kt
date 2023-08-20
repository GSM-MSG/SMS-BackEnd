package team.msg.sms.domain.student.usecase

import team.msg.sms.common.annotation.UseCase
import team.msg.sms.domain.certificate.service.CertificateService
import team.msg.sms.domain.region.model.Region
import team.msg.sms.domain.region.service.RegionService
import team.msg.sms.domain.student.dto.req.ModifyStudentInfoRequestData
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
class ModifyStudentInfoUseCase(
    private val userService: UserService,
    private val studentService: StudentService,
    private val studentTechStackService: StudentTechStackService,
    private val techStackService: TechStackService,
    private val regionService: RegionService,
    private val certificateService: CertificateService
) {
    fun execute(modifyStudentInfoData: ModifyStudentInfoRequestData) {
        val user = userService.getCurrentUser()

        val student = studentService.getStudentByUser(user)
        val studentTechStacks = studentTechStackService.getStudentTechStackByStudentId(studentId = student.id)
        val techStacks = techStackService.getAllTechStack()
        val techStackNames = studentTechStackService.getMatchTechStackNameWithId(studentTechStacks, techStacks)
        val regions = regionService.getRegionByStudentUuid(student.id)

        val modifyStudentInfoDataModel = toStudentModel(modifyStudentInfoData, user)

        // 학생 정보 수정
        val checkStudentMismatch = studentService.checkStudentDataMismatch(student, modifyStudentInfoDataModel)
        if(checkStudentMismatch) {
            studentService.saveStudent(modifyStudentInfoDataModel.copy(id = student.id), user)
        }

        // 기술 스택 지우기 수정
        val removedTechStacks = studentTechStackService.checkRemovedTechStacks(techStackNames, modifyStudentInfoData.studentTechStacks)
        if(removedTechStacks.isNotEmpty()) {
            removedTechStacks.forEach {
                val findTechStackModel = findTechStackModel(techStacks, it)
                val count = findTechStackModel.count - 1
                studentTechStackService.deleteByStudentAndTechStack(student, findTechStackModel)
                if(count == -1) {
                    techStackService.deleteByTechStack(findTechStackModel)
                } else {
                    techStackService.save(findTechStackModel.copy(count = count))
                }
            }
        }

        // 기술 스택 추가 수정
        val addedTechStacks = studentTechStackService.checkAddedTechStacks(techStackNames, modifyStudentInfoData.studentTechStacks)
        if(addedTechStacks.isNotEmpty()) {
            updateStudentTechStacks(techStacks.toMutableList(), addedTechStacks, student.id)
        }

        val removedRegions = regionService.checkRemovedRegion(regions, modifyStudentInfoData.region)
        if(removedRegions.isNotEmpty()) {
            removedRegions.forEach {
                regionService.deleteByRegion(it, student)
            }
        }

        certificateService.

        val addedRegions = regionService.checkAddedRegion(regions, modifyStudentInfoData.region)
        if(addedRegions.isNotEmpty()) {
            val regions = addedRegions.map { toRegionModel(it, student.id) }
            regionService.saveAll(regions, student, user)
        }
    }

    private fun toStudentModel(modifyStudentInfoData: ModifyStudentInfoRequestData, user: User): Student =
        Student(
            id = UUID.randomUUID(),
            department = findDepartment(user.stuNum),
            contactEmail = modifyStudentInfoData.contactEmail,
            major = modifyStudentInfoData.major,
            portfolioUrl = modifyStudentInfoData.portfolioUrl,
            gsmAuthenticationScore = modifyStudentInfoData.gsmAuthenticationScore,
            salary = modifyStudentInfoData.salary,
            formOfEmployment = modifyStudentInfoData.formOfEmployment,
            introduce = modifyStudentInfoData.introduce,
            militaryService = modifyStudentInfoData.militaryService,
            profileImgUrl = modifyStudentInfoData.profileImgUrl,
            userId = user.id
        )

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

    private fun findTechStackModel(
        techStacks: List<TechStack>,
        removedTechStack: String
    ) = techStacks.find { it.stack == removedTechStack }!!

    private fun updateStudentTechStacks(stack: MutableList<TechStack>, studentTechStacks: List<String>, studentId: UUID) {
        studentTechStacks.forEach { stackItem ->
            val techStackData = stack.find { it.stack == stackItem }
            if (techStackData == null) {
                val techStack = techStackService.save(toStackModel(stackItem))
                stack.add(0, techStack)
                studentTechStackService.save(toStudentTechStackModel(studentId, techStack.id))
            } else {
                val techStack = techStackService.save(techStack = techStackData.copy(count = techStackData.count + 1))
                stack.add(0, techStack)
                studentTechStackService.save(toStudentTechStackModel(studentId, techStack.id))
            }
        }
    }

    private fun toStudentTechStackModel(studentId: UUID, techStackId: Long): StudentTechStack =
        StudentTechStack(
            id = 0,
            studentId = studentId,
            techStackId = techStackId
        )

    private fun toStackModel(stack: String): TechStack =
        TechStack(
            id = 0,
            stack = stack,
            count = 0
        )

    private fun toRegionModel(region: String, studentId: UUID) =
        Region(
            id = 0,
            region = region,
            studentId = studentId
        )
}