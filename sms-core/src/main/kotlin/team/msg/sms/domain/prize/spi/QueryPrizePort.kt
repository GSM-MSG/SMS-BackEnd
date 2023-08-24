package team.msg.sms.domain.prize.spi

import team.msg.sms.domain.prize.model.Prize
import java.util.*

interface QueryPrizePort {
    fun queryAllPrizeByStudentId(studentId: UUID): List<Prize>
}