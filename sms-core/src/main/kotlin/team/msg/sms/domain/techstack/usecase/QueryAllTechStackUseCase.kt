package team.msg.sms.domain.techstack.usecase

import team.msg.sms.common.annotation.UseCase
import team.msg.sms.domain.techstack.dto.TechStacksResponse
import team.msg.sms.domain.techstack.service.TechStackService

@UseCase
class QueryAllTechStackUseCase(
    private val techStackService: TechStackService
) {
    fun execute(stack: String?): TechStacksResponse {
        return TechStacksResponse(
            techStack = if (stack == null) techStackService.getAllTechStack()
                .map { it.stack } else techStackService.getAllTechStackByStack(
                stack
            ).map { it.stack }
        )
    }
}