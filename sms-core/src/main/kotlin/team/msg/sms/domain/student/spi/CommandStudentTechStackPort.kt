package team.msg.sms.domain.student.spi

import team.msg.sms.domain.student.model.Student
import team.msg.sms.domain.student.model.StudentTechStack
import team.msg.sms.domain.techstack.model.TechStack

interface CommandStudentTechStackPort {
    fun save(studentTechStack: StudentTechStack)
    fun deleteAllByStudent(student: Student)
    fun deleteByStudentAndTechStack(student: Student, techStack: TechStack)
}