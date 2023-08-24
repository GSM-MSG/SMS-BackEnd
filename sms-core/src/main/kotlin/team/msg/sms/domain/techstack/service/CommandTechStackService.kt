package team.msg.sms.domain.techstack.service

import team.msg.sms.domain.techstack.model.TechStack

interface CommandTechStackService {
    fun save(techStack: TechStack): TechStack
    fun saveAll(techStack: List<TechStack>): List<TechStack>
    fun deleteByTechStack(techStack: TechStack)
    fun decrementTechStackCount(techStack: TechStack)
}