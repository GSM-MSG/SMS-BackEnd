package team.msg.sms.domain.student.usecase

import team.msg.sms.common.annotation.UseCase
import team.msg.sms.domain.student.dto.response.StudentInfoListResponse
import team.msg.sms.domain.student.service.StudentService

@UseCase
class FindAllUseCase(
    private val studentService: StudentService
) {
    fun execute(page: Int, size: Int): StudentInfoListResponse {
        val studentsWithPage = studentService.getStudentsWithPage(page, size)
        studentsWithPage.students
            .map {
                StudentInfoListResponse(
                    content = StudentInfoListResponse.StudentContentInfo(
                        profileImg = it.profileImgUrl,
                        name = it.userId
                    )
                )
            }
        StudentInfoListResponse(
            content =
        )
        return studentsWithPage
    }
}