package team.msg.sms.domain.teacher.service

import team.msg.sms.domain.user.model.User

interface CheckTeacherService {
    fun checkNewTeacher(user: User): Boolean
}