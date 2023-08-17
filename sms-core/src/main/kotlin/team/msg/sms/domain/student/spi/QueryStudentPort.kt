package team.msg.sms.domain.student.spi

import team.msg.sms.domain.student.model.Student
import team.msg.sms.domain.user.model.User
import java.util.UUID

interface QueryStudentPort {
    fun queryStudentById(uuid: UUID): Student.StudentWithUserInfo?
    fun queryStudentsWithPage(page: Int, size: Int): Student.StudentWithPageInfo
    fun queryStudentByUserId(userId: UUID): Student
    fun queryStudentUserInfoByUserId(userId: UUID): Student.StudentWithUserInfo?
    fun queryStudentByUser(user: User): Student
    fun searchStudent(): List<Student.StudentWithUserInfo>
    fun existsStudentById(uuid: UUID): Boolean
    fun existsStudentByUser(user: User): Boolean
}