package team.msg.sms.domain.techstack.service

import team.msg.sms.domain.techstack.model.TechStack

interface GetTechStackService {
    fun findAll(): List<TechStack>
    fun findAllByStack(stack: String): List<TechStack>
}