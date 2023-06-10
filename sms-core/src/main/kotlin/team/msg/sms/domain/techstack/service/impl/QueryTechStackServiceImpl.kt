package team.msg.sms.domain.techstack.service.impl

import org.springframework.stereotype.Service
import team.msg.sms.domain.techstack.model.TechStack
import team.msg.sms.domain.techstack.service.QueryTechStackService
import team.msg.sms.domain.techstack.spi.TechStackPort

@Service
class QueryTechStackServiceImpl(
    private val techStackPort: TechStackPort
) : QueryTechStackService {
    override fun getAllTechStack(): List<TechStack> =
        techStackPort.findAll()
}