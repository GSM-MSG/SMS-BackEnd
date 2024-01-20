package team.msg.sms.domain.auth.usecase

import team.msg.sms.common.annotation.UseCase
import team.msg.sms.domain.auth.dto.res.VerifyAccessResponseData
import team.msg.sms.domain.student.service.StudentService
import team.msg.sms.domain.teacher.service.TeacherService
import team.msg.sms.domain.user.exception.RoleNotExistsException
import team.msg.sms.domain.user.service.UserService

@UseCase
class VerifyAccessUseCase(
    private val userService: UserService,
    private val studentService: StudentService,
    private val teacherService: TeacherService
) {
    fun execute(): VerifyAccessResponseData =
        userService.getCurrentUser()
            .let {
                VerifyAccessResponseData(
                    when (it.roles[0].name) {
                        "ROLE_STUDENT" -> studentService.checkNewStudent(it)
                        "ROLE_TEACHER" -> teacherService.checkNewTeacher(it)
                        else -> throw RoleNotExistsException
                    },
                    it.roles[0].name
                )
            }
}