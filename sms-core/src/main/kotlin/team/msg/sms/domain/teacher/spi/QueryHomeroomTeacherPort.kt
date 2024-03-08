package team.msg.sms.domain.teacher.spi

import team.msg.sms.domain.teacher.model.HomeroomTeacher
import java.util.UUID

interface QueryHomeroomTeacherPort {
    fun existsHomeroomTeacherByGradeAndClassNum(grade: Int, classNum: Int): Boolean
    fun queryHomeroomTeacherByUserId(userId: UUID): HomeroomTeacher?
}