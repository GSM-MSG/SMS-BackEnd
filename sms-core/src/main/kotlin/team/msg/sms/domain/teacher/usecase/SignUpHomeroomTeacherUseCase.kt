package team.msg.sms.domain.teacher.usecase

import org.springframework.transaction.annotation.Transactional
import team.msg.sms.common.annotation.UseCase
import team.msg.sms.domain.teacher.dto.req.SignUpHomeroomTeacherRequestData
import team.msg.sms.domain.teacher.model.HomeroomTeacher
import team.msg.sms.domain.teacher.service.CommandHomeroomTeacherService
import team.msg.sms.domain.teacher.service.HomeroomTeacherService
import team.msg.sms.domain.teacher.service.TeacherService
import team.msg.sms.domain.user.service.UserService

@UseCase
class SignUpHomeroomTeacherUseCase(
    private val userService: UserService,
    private val teacherService: TeacherService,
    private val homeroomTeacherService: HomeroomTeacherService
) {

    @Transactional(rollbackFor = [Exception::class])
    fun execute(signUpHomeroomTeacherRequestData: SignUpHomeroomTeacherRequestData) {
        val user = userService.getCurrentUser()

        teacherService.checkTeacherExistsByUser(user)

        val teacher = teacherService.saveTeacher(user)
        val grade = signUpHomeroomTeacherRequestData.grade
        val classNum = signUpHomeroomTeacherRequestData.classNum

        homeroomTeacherService.checkHomeroomTeacherExistsByGradeAndClassNum(grade, classNum)

        val homeroomTeacher = HomeroomTeacher(
            grade = grade,
            classNum = classNum,
            teacherId = teacher.id
        )

        homeroomTeacherService.saveHomeroomTeacher(homeroomTeacher, teacher, user)
    }
}