package team.msg.sms.domain.student.spi

import team.msg.sms.domain.student.model.Student
import team.msg.sms.domain.user.model.User
import java.util.UUID

interface QueryStudentPort {
    fun queryStudentWithUserInfoById(uuid: UUID): Student.StudentWithUserInfo?
    fun queryStudentById(uuid: UUID): Student?
    fun queryStudentsWithPage(page: Int, size: Int): Student.StudentWithPageInfo
    fun queryStudentIds(): List<UUID>
    fun queryStudentIdsByGradeAndClassNum(grade: Int, classNum: Int): List<UUID>
    fun queryStudentByUserId(userId: UUID): Student
    fun queryStudentUserInfoByUserId(userId: UUID): Student.StudentWithUserInfo?
    fun queryStudentByUser(user: User): Student
    fun searchStudent(): List<Student.StudentWithUserInfo>
    fun existsStudentById(uuid: UUID): Boolean
    fun existsStudentByUser(user: User): Boolean
}