package team.msg.sms.domain.teacher.service

import team.msg.sms.domain.teacher.model.Teacher
import java.util.UUID

interface GetTeacherService{
    fun findTeacherByUuid(uuid: UUID?): Teacher
}