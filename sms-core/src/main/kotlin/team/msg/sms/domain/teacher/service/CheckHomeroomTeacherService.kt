package team.msg.sms.domain.teacher.service

import team.msg.sms.domain.teacher.model.Teacher

interface CheckHomeroomTeacherService {
    fun checkTeacherExistsByTeacher(teacher: Teacher)
}