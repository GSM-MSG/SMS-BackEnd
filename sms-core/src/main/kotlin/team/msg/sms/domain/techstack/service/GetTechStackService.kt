package team.msg.sms.domain.techstack.service

import team.msg.sms.domain.techstack.model.TechStack

interface GetTechStackService {
    fun getAllTechStack(): List<TechStack>
    fun getAllTechStackByCount(): List<TechStack>
    fun getAllTechStackByStack(stack: String): List<TechStack>
    fun getTechStackByStack(stack: String): TechStack
}