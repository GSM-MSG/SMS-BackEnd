package team.msg.sms.domain.student.usecase

import team.msg.sms.common.annotation.UseCase
import team.msg.sms.domain.student.dto.res.DetailStudentInfoAnonymousResponseData
import team.msg.sms.domain.student.service.StudentService
import team.msg.sms.domain.student.service.StudentTechStackService
import team.msg.sms.domain.techstack.service.TechStackService

@UseCase
class StudentInfoAnonymousUseCase(
    private val studentService: StudentService,
    private val techStackService: TechStackService,
    private val studentTechStackService: StudentTechStackService
) {
    fun execute(uuid: String): DetailStudentInfoAnonymousResponseData {
        val student = studentService.getStudentUserInfoByUuid(uuid)
        val techStack = techStackService.getAllTechStack()
        val studentTechStack = studentTechStackService.getStudentTechStackByStudentId(studentId = student.id)

        return DetailStudentInfoAnonymousResponseData(
            name = student.name.replaceRange(1 until student.name.length, "*".repeat(2)),
            introduce = student.introduce,
            major = student.major,
            profileImg = "",
            techStack = studentTechStack.map { studentTechStack ->
                techStack.find { it.id == studentTechStack.techStackId }?.stack ?: ""
            }
        )
    }
}