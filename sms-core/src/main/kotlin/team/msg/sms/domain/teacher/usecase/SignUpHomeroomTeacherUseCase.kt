package team.msg.sms.domain.teacher.usecase

import org.springframework.transaction.annotation.Transactional
import team.msg.sms.common.annotation.UseCase
import team.msg.sms.domain.teacher.dto.req.SignUpHomeroomTeacherRequestData
import team.msg.sms.domain.teacher.service.TeacherService
import team.msg.sms.domain.user.service.UserService

@UseCase
class SignUpHomeroomTeacherUseCase(
    private val userService: UserService,
    private val teacherService: TeacherService
) {

    @Transactional(rollbackFor = [Exception::class])
    fun execute(signUpHomeroomTeacherRequestData: SignUpHomeroomTeacherRequestData) {
        val user = userService.getCurrentUser()

        teacherService.checkTeacherExistsByUser(user)

        teacherService.saveTeacher(user)
    }
}