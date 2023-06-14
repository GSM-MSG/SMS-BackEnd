package team.msg.sms.domain.techstack.service.impl

import org.springframework.stereotype.Service
import team.msg.sms.domain.techstack.model.TechStack
import team.msg.sms.domain.techstack.service.GetTechStackService
import team.msg.sms.domain.techstack.spi.TechStackPort
import java.util.*

@Service
class GetTechStackServiceImpl(
    private val techStackPort: TechStackPort
) : GetTechStackService {
    override fun getAllTechStack(): List<TechStack> =
        techStackPort.queryAll()

    override fun getTechStackByStudentUuid(uuid: UUID): List<TechStack> =
        techStackPort.queryByStudentUuid(uuid)

    override fun getAllTechStackByStack(stack: String): List<TechStack> =
        techStackPort.queryAllByStack(stack)
}