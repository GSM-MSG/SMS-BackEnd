package team.msg.sms.domain.techstack.usecase

import team.msg.sms.common.annotation.UseCase
import team.msg.sms.domain.techstack.dto.res.TechStacksResponseData
import team.msg.sms.domain.techstack.service.TechStackService

@UseCase
class QueryAllTechStackUseCase(
    private val techStackService: TechStackService
) {
    fun execute(stack: String?): TechStacksResponseData {
        val techStack = if (stack == null) techStackService.getAllTechStack()
            .map { it.stack }.distinct() else techStackService.getAllTechStackByStack(
            stack
        ).map { it.stack }.distinct()

        return TechStacksResponseData(
            techStack = if (techStack.size > 30) techStack
                .slice(0..30)
            else techStack
        )
    }
}