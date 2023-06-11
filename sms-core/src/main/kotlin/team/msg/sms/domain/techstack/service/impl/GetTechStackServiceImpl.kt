package team.msg.sms.domain.techstack.service.impl

import team.msg.sms.common.annotation.Service
import team.msg.sms.domain.techstack.model.TechStack
import team.msg.sms.domain.techstack.service.GetTechStackService
import team.msg.sms.domain.techstack.spi.TechStackPort

@Service
class GetTechStackServiceImpl(
    private val techStackPort: TechStackPort
) : GetTechStackService {
    override fun findAll(): List<TechStack> = techStackPort.queryAll()

    override fun findAllByStack(stack: String): List<TechStack> = techStackPort.queryAllByStack(stack)
}