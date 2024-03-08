package team.msg.sms.domain.teacher.service

import team.msg.sms.domain.teacher.model.HomeroomTeacher
import java.util.UUID

interface GetHomeroomTeacherService {
    fun getHomeroomTeacherByUserId (userId: UUID): HomeroomTeacher
}