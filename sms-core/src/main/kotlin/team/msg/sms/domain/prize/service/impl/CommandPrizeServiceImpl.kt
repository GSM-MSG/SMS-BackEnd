package team.msg.sms.domain.prize.service.impl

import team.msg.sms.common.annotation.Service
import team.msg.sms.domain.prize.model.Prize
import team.msg.sms.domain.prize.service.CommandPrizeService
import team.msg.sms.domain.prize.spi.PrizePort
import team.msg.sms.domain.student.model.Student

@Service
class CommandPrizeServiceImpl(
    private val prizePort: PrizePort
) : CommandPrizeService {
    override fun saveAll(prizes: List<Prize>) =
        prizePort.saveAll(prizes)

    override fun deleteAllByStudent(student: Student) =
        prizePort.deleteAllByStudent(student)
}