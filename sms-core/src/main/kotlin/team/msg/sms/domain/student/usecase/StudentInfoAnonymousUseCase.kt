package team.msg.sms.domain.student.usecase

import team.msg.sms.common.annotation.UseCase
import team.msg.sms.domain.student.dto.response.DetailStudentInfoAnonymousResponse
import team.msg.sms.domain.student.service.StudentService
import team.msg.sms.domain.techstack.service.TechStackService

@UseCase
class StudentInfoAnonymousUseCase(
    private val studentService: StudentService,
    private val techStackService: TechStackService
) {
    fun execute(uuid: String): DetailStudentInfoAnonymousResponse {
        val student = studentService.getStudnetByUuid(uuid)
        val techStackByStudentUuid = techStackService.getTechStackByStudentUuid(student.id)
        val techStacks: List<String> = techStackByStudentUuid.map { it.stack }

        return DetailStudentInfoAnonymousResponse(
            name = student.name.replaceRange(1 until student.name.length, "*".repeat(2)),
            introduce = student.introduce,
            major = student.major,
            profileImg = "",
            techStack = techStacks
        )
    }
}