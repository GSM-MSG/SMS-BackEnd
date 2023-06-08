package team.msg.sms.domain.student.usecase

import team.msg.sms.common.annotation.UseCase
import team.msg.sms.domain.student.dto.response.StudentInfoListResponse
import team.msg.sms.domain.student.service.StudentService

@UseCase
class FindAllUseCase(
    private val studentService: StudentService
) {
    fun execute(page: Int, size: Int): StudentInfoListResponse {
        val studentsWithPageInfo = studentService.getStudentsWithPage(page, size)

        return StudentInfoListResponse(
            content = studentsWithPageInfo.students,
            page = studentsWithPageInfo.page,
            contentSize = studentsWithPageInfo.contentSize,
            totalSize = studentsWithPageInfo.totalSize,
            last = studentsWithPageInfo.last
        )
    }
}