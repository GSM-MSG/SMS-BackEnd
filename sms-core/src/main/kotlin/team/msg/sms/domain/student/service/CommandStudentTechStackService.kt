package team.msg.sms.domain.student.service

import team.msg.sms.domain.student.model.Student
import team.msg.sms.domain.student.model.StudentTechStack

interface CommandStudentTechStackService {
    fun save(studentTechStack: StudentTechStack)
    fun deleteAllByStudent(student: Student)
}