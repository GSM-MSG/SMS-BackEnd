package team.msg.sms.domain.techstack.spi

import team.msg.sms.domain.techstack.model.TechStack

interface CommandTechStackPort {
    fun save(techStack: TechStack): TechStack
    fun saveAll(techStack: List<TechStack>): List<TechStack>
    fun deleteByTechStack(techStack: TechStack)
    fun decrementTechStackCount(techStack: TechStack)
}