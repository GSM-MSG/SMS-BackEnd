package team.msg.sms.domain.major.service

import team.msg.sms.common.annotation.Service

@Service
class MajorService(
    queryMajorService: QueryMajorService
) : QueryMajorService by queryMajorService