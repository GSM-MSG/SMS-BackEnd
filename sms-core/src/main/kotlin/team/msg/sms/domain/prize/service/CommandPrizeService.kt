package team.msg.sms.domain.prize.service

import team.msg.sms.domain.prize.model.Prize
import team.msg.sms.domain.student.model.Student

interface CommandPrizeService {
    fun saveAll(prizes: List<Prize>)
    fun deleteAllByStudent(student: Student)
    fun deleteByPrize(prize: Prize, student: Student)
}