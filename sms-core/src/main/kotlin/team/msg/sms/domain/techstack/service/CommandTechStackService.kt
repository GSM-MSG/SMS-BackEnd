package team.msg.sms.domain.techstack.service

import team.msg.sms.domain.student.model.Student
import team.msg.sms.domain.techstack.model.TechStack
import team.msg.sms.domain.user.model.User

interface CommandTechStackService {
    fun saveAll(techStack: List<TechStack>, student: Student, user: User): List<TechStack>
    fun deleteAllByStudent(student: Student, user: User)
}