package team.msg.sms.domain.major.usecase

import team.msg.sms.common.annotation.UseCase
import team.msg.sms.domain.major.model.Major
import team.msg.sms.domain.major.service.MajorService

@UseCase
class QueryAllMajorUseCase(
    private val majorService: MajorService
) {
    fun execute(): List<Major> =
        majorService.getAllMajor()
}