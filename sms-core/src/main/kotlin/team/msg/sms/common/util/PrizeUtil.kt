package team.msg.sms.common.util

import team.msg.sms.domain.prize.dto.res.PrizeResponseData
import team.msg.sms.domain.prize.model.Prize

object PrizeUtil {
    fun generatePrizeResponseData(prizes: List<Prize>): List<PrizeResponseData> =
        prizes
            .map {
                PrizeResponseData(
                    name = it.name,
                    type = it.type,
                    date = it.date
                )
            }

}