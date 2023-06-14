package team.msg.sms.domain.major.service

import team.msg.sms.domain.major.model.Major

interface GetMajorService {
    fun getAllMajor(): List<Major>
}