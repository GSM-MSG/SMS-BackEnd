package team.msg.sms.domain.authentication.usecase

import team.msg.sms.common.annotation.UseCase
import team.msg.sms.domain.authentication.dto.res.QueryAuthenticationDetailsResponseData
import team.msg.sms.domain.authentication.service.GetAuthenticationHistoryService
import team.msg.sms.domain.authentication.spi.QueryAuthenticationPort
import java.util.*

@UseCase
class QueryAuthenticationDetailsUseCase(
    private val getAuthenticationPort: QueryAuthenticationPort,
    private val getAuthenticationHistoryService: GetAuthenticationHistoryService
) {
    fun execute(uuid: String): QueryAuthenticationDetailsResponseData {
        val authentication = getAuthenticationPort.queryAuthenticationByUuid(UUID.fromString(uuid))
        val history = getAuthenticationHistoryService.getLatestAuthenticationHistory(authentication)

        return QueryAuthenticationDetailsResponseData(
            id = authentication.id,
            title = authentication.title,
            content = authentication.title,
            activityImages = authentication.activityImages,
            lastModifiedDate = history.createdAt,
            score = authentication.score,
            activityStatus = authentication.activityStatus
        )
    }
}