package team.msg.sms.domain.techstack.spi

import team.msg.sms.domain.student.model.Student
import team.msg.sms.domain.techstack.model.TechStack

interface QueryTechStackPort {
    fun queryAll(): List<TechStack>
    fun queryAllByCount(): List<TechStack>
    fun queryAllByStack(stack: String): List<TechStack>
}