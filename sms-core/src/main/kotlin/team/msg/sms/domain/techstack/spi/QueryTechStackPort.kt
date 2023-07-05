package team.msg.sms.domain.techstack.spi

import team.msg.sms.domain.techstack.model.TechStack
import java.util.UUID

interface QueryTechStackPort {
    fun queryByStudentUuid(uuid: UUID): List<TechStack>
    fun queryAll(): List<TechStack>
    fun queryAllByStack(stack: String): List<TechStack>
}