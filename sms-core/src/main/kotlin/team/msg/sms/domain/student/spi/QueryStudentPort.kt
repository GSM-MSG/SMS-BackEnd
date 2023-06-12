package team.msg.sms.domain.student.spi

import team.msg.sms.domain.student.model.Student
import team.msg.sms.domain.user.model.User
import java.util.UUID

interface QueryStudentPort {
    fun queryStudentById(uuid: UUID): Student.StudentWithUserInfo?

    fun existsStudentById(uuid: UUID): Boolean

    fun existsStudentByUser(user: User): Boolean

    fun getStudentsWithPage(page: Int, size: Int): Student.StudentWithPageInfo

    fun getStudentByUserId(userId: UUID): Student

    fun queryStudentByUser(user: User): Student
}