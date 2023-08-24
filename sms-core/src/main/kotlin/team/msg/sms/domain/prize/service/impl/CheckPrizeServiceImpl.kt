package team.msg.sms.domain.prize.service.impl

import team.msg.sms.common.annotation.Service
import team.msg.sms.domain.prize.model.Prize
import team.msg.sms.domain.prize.service.CheckPrizeService

@Service
class CheckPrizeServiceImpl(

) : CheckPrizeService {
    override fun checkAddedPrize(prizes: List<Prize>, modifyPrizes: List<Prize>): List<Prize> {
        return modifyPrizes.filterNot { modifiedPrize ->
            prizes.any { existingPrize ->
                existingPrize.name == modifiedPrize.name &&
                        existingPrize.type == modifiedPrize.type &&
                        existingPrize.date == modifiedPrize.date
            }
        }
    }

    override fun checkRemovedPrize( prizes: List<Prize>, modifyPrizes: List<Prize>): List<Prize> {
        return prizes.filterNot { existingPrize ->
            modifyPrizes.any { modifiedPrize ->
                modifiedPrize.name == existingPrize.name &&
                        modifiedPrize.type == existingPrize.type &&
                        modifiedPrize.date == existingPrize.date
            }
        }
    }
}