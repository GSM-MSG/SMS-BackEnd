package team.msg.sms.domain.major.service.impl

import team.msg.sms.common.annotation.Service
import team.msg.sms.domain.major.model.Major
import team.msg.sms.domain.major.service.QueryMajorService
import team.msg.sms.domain.major.spi.MajorPort

@Service
class QueryMajorServiceImpl(
    private val majorPort: MajorPort
) : QueryMajorService {
    override fun getAllMajor(): List<Major> =
        majorPort.queryAll()
}