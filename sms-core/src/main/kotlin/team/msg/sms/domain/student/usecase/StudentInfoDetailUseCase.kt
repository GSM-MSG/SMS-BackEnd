package team.msg.sms.domain.student.usecase

import team.msg.sms.common.annotation.UseCase
import team.msg.sms.domain.student.dto.res.DetailStudentInfoResponseData
import team.msg.sms.domain.student.service.StudentService
import team.msg.sms.domain.techstack.service.TechStackService


@UseCase
class StudentInfoDetailUseCase(
    private val studentService: StudentService,
    private val techStackService: TechStackService
) {
    fun execute(uuid: String): DetailStudentInfoResponseData {
        val student = studentService.getStudentByUuid(uuid)
        val techStackByStudentUuid = techStackService.getTechStackByStudentUuid(student.id)
        val techStacks: List<String> = techStackByStudentUuid.map { it.stack }

        return DetailStudentInfoResponseData(
            name = student.name,
            introduce = student.introduce,
            grade = student.stuNum.substring(0, 1).toInt(),
            classNum = student.stuNum.substring(1, 2).toInt(),
            number = student.stuNum.substring(2, 4).toInt(),
            department = student.department,
            major = student.major,
            profileImg = student.profileImgUrl,
            techStack = techStacks,
        )
    }
}