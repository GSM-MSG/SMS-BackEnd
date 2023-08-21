package team.msg.sms.domain.prize.spi

import team.msg.sms.domain.prize.model.Prize
import team.msg.sms.domain.student.model.Student

interface CommandPrizePort {
    fun saveAll(prizes: List<Prize>)
    fun deleteAllByStudent(student: Student)
    fun deleteByPrize(prize: Prize, student: Student)
}