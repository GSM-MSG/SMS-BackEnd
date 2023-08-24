package team.msg.sms.domain.prize.service

import team.msg.sms.domain.prize.model.Prize

interface CheckPrizeService {
    fun checkAddedPrize(prizes: List<Prize>, modifyPrizes: List<Prize>): List<Prize>
    fun checkRemovedPrize(prizes: List<Prize>, modifyPrizes: List<Prize>): List<Prize>
}