package team.msg.sms.domain.auth.usecase

import team.msg.sms.common.annotation.UseCase
import team.msg.sms.domain.auth.dto.res.VerifyAccessResponseData
import team.msg.sms.domain.student.service.StudentService
import team.msg.sms.domain.user.service.UserService

@UseCase
class VerifyAccessUseCase(
    private val userService: UserService,
    private val studentService: StudentService
) {
    fun execute(): VerifyAccessResponseData =
        userService.getCurrentUser()
            .let {
                VerifyAccessResponseData(
                    studentService.checkNewStudent(it, it.roles[0].name),
                    it.roles[0].name
                )
            }
}