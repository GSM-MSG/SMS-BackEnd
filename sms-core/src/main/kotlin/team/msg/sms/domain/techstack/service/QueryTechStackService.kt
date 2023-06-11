package team.msg.sms.domain.techstack.service

import team.msg.sms.domain.techstack.model.TechStack

interface QueryTechStackService {
    fun getAllTechStack(): List<TechStack>

    fun getAllTechStackByStack(stack: String): List<TechStack>
}