package team.msg.sms.domain.teacher.spi

import java.util.*

interface QueryTeacherPort {
    fun existsTeacherById(uuid: UUID): Boolean
}