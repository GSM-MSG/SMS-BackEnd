package team.msg.sms.domain.teacher.usecase

import org.springframework.transaction.annotation.Transactional
import team.msg.sms.common.annotation.UseCase
import team.msg.sms.domain.auth.model.Role
import team.msg.sms.domain.teacher.dto.req.SignUpHomeroomTeacherRequestData
import team.msg.sms.domain.teacher.model.HomeroomTeacher
import team.msg.sms.domain.teacher.model.Teacher
import team.msg.sms.domain.teacher.service.HomeroomTeacherService
import team.msg.sms.domain.teacher.service.TeacherService
import team.msg.sms.domain.user.service.UserService
import java.util.*

@UseCase
class SignUpHomeroomTeacherUseCase(
    private val userService: UserService,
    private val teacherService: TeacherService,
    private val homeroomTeacherService: HomeroomTeacherService
) {

    @Transactional(rollbackFor = [Exception::class])
    fun execute(signUpHomeroomTeacherRequestData: SignUpHomeroomTeacherRequestData) {
        val user = userService.getCurrentUser()
        val grade = signUpHomeroomTeacherRequestData.grade
        val classNum = signUpHomeroomTeacherRequestData.classNum

        teacherService.checkTeacherExistsByUser(user)
        homeroomTeacherService.checkHomeroomTeacherExistsByGradeAndClassNum(grade, classNum)

        val teacher = Teacher(
            id = UUID.randomUUID(),
            userId = user.id,
        )

        val savedTeacher = teacherService.saveTeacher(teacher, user)

        val homeroomTeacher = HomeroomTeacher(
            grade = grade,
            classNum = classNum,
            teacherId = savedTeacher.id
        )

        homeroomTeacherService.saveHomeroomTeacher(homeroomTeacher, savedTeacher, user)

        userService.saveRoles(user, mutableListOf(Role.ROLE_HOMEROOM))
    }
}