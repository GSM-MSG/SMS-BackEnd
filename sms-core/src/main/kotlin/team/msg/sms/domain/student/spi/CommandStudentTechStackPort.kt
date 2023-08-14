package team.msg.sms.domain.student.spi

import team.msg.sms.domain.student.model.Student
import team.msg.sms.domain.student.model.StudentTechStack

interface CommandStudentTechStackPort {
    fun save(studentTechStack: StudentTechStack)
    fun deleteAllByStudent(student: Student)
}