package team.msg.sms.domain.major.spi

import team.msg.sms.domain.major.model.Major

interface QueryMajorPort {
    fun queryAll(): List<Major>
}