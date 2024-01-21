package team.msg.sms.domain.teacher.service.impl

import team.msg.sms.common.annotation.Service
import team.msg.sms.domain.teacher.model.Teacher
import team.msg.sms.domain.teacher.service.CommandTeacherService
import team.msg.sms.domain.teacher.spi.TeacherPort
import team.msg.sms.domain.user.model.User
import java.util.*

@Service
class CommandTeacherServiceImpl(
    private val teacherPort: TeacherPort
) : CommandTeacherService {
    override fun saveTeacher(user: User): Teacher {
        val teacher = Teacher(
            id = UUID.randomUUID(),
            userId = user.id,
        )

        return teacherPort.saveTeacher(teacher, user)
    }
}