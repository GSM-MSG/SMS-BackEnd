package team.msg.sms.domain.teacher.usecase

import org.springframework.transaction.annotation.Transactional
import team.msg.sms.common.annotation.UseCase
import team.msg.sms.domain.auth.model.Role
import team.msg.sms.domain.teacher.model.Teacher
import team.msg.sms.domain.teacher.service.TeacherService
import team.msg.sms.domain.user.service.UserService
import java.util.*

@UseCase
class SignUpDeputyPrincipalTeacherUseCase(
    private val userService: UserService,
    private val teacherService: TeacherService
){

    @Transactional(rollbackFor = [Exception::class])
    fun execute(){
        val user = userService.getCurrentUser()

        teacherService.checkTeacherExistsByUser(user)

        val teacher = Teacher(
            id = UUID.randomUUID(),
            userId = user.id,
        )

        teacherService.saveDeputyPrincipalTeacher(teacher, user)

        userService.saveRoles(user, mutableListOf(Role.ROLE_DEPUTY_PRINCIPAL))
    }
}