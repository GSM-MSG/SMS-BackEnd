package team.msg.sms.domain.teacher.service.impl

import team.msg.sms.common.annotation.Service
import team.msg.sms.domain.teacher.model.Teacher
import team.msg.sms.domain.teacher.service.CommandTeacherService
import team.msg.sms.domain.teacher.spi.TeacherPort
import team.msg.sms.domain.user.model.User

@Service
class CommandTeacherServiceImpl(
    private val teacherPort: TeacherPort
) : CommandTeacherService {
    override fun saveTeacher(teacher: Teacher, user: User) =
        teacherPort.saveTeacher(teacher, user)

    override fun saveDirectorTeacher(teacher: Teacher, user: User) =
        saveTeacher(teacher, user)

    override fun saveDeputyPrincipalTeacher(teacher: Teacher, user: User) =
        saveTeacher(teacher, user)

    override fun savePrincipalTeacher(teacher: Teacher, user: User) =
        saveTeacher(teacher, user)
}