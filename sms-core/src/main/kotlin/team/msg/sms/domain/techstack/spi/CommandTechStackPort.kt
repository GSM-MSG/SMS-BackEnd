package team.msg.sms.domain.techstack.spi

import team.msg.sms.domain.student.model.Student
import team.msg.sms.domain.techstack.model.TechStack
import team.msg.sms.domain.user.model.User

interface CommandTechStackPort {
    fun saveAll(techStack: List<TechStack>, student: Student, user: User): List<TechStack>
}