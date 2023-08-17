package team.msg.sms.domain.prize.service

import team.msg.sms.domain.prize.model.Prize
import java.util.*

interface GetPrizeService {
    fun getAllPrizeByStudentId(studentId: UUID): List<Prize>
}