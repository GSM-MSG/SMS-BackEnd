package team.msg.sms.domain.major.service

import team.msg.sms.domain.major.model.Major

interface QueryMajorService {
    fun getAllMajor(): List<Major>
}