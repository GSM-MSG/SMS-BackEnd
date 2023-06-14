package team.msg.sms.domain.techstack.service

import team.msg.sms.domain.techstack.model.TechStack
import java.util.UUID

interface GetTechStackService {
    fun getAllTechStack(): List<TechStack>

    fun getTechStackByStudentUuid(uuid: UUID): List<TechStack>
    fun getAllTechStackByStack(stack: String): List<TechStack>
}