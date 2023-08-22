package team.msg.sms.domain.student.usecase

import team.msg.sms.common.annotation.UseCase
import team.msg.sms.domain.student.service.StudentService
import team.msg.sms.domain.user.model.User

@UseCase
class ExistStudentUseCase(
    private val studentService: StudentService
) {
    fun execute(user: User) =
        studentService.studentExistsByUser(user)
}