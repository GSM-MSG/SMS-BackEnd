package team.msg.sms.domain.prize.service.impl

import team.msg.sms.common.annotation.Service
import team.msg.sms.domain.prize.model.Prize
import team.msg.sms.domain.prize.service.GetPrizeService
import team.msg.sms.domain.prize.spi.PrizePort
import java.util.*

@Service
class GetPrizeServiceImpl(
    private val prizePort: PrizePort
) : GetPrizeService {
    override fun getAllPrizeByStudentId(studentId: UUID): List<Prize> =
        prizePort.queryAllPrizeByStudentId(studentId)

}