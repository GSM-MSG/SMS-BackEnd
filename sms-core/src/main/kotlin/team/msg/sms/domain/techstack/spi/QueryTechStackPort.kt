package team.msg.sms.domain.techstack.spi

import team.msg.sms.domain.techstack.model.TechStack

interface QueryTechStackPort {
    fun findAll(): List<TechStack>
}