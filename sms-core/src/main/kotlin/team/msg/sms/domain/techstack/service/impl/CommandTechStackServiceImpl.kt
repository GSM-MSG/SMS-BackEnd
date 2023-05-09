package team.msg.sms.domain.techstack.service.impl

import team.msg.sms.common.annotation.Service
import team.msg.sms.domain.student.model.Student
import team.msg.sms.domain.techstack.model.TechStack
import team.msg.sms.domain.techstack.service.CommandTechStackService
import team.msg.sms.domain.techstack.spi.TechStackPort
import team.msg.sms.domain.user.model.User

@Service
class CommandTechStackServiceImpl(
    private val techStackPort: TechStackPort
) : CommandTechStackService {
    override fun saveAll(techStack: List<TechStack>, student: Student, user: User): List<TechStack> =
        techStackPort.saveAll(techStack, student, user)
}