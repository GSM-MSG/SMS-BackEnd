package team.msg.sms.domain.student.spi

import team.msg.sms.domain.student.model.Student
import java.util.UUID

interface QueryStudentPort {
    fun queryStudentById(uuid: UUID): Student?

    fun existsStudentById(uuid: UUID): Boolean
}