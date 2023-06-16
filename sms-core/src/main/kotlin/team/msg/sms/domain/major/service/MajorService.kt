package team.msg.sms.domain.major.service

import team.msg.sms.common.annotation.Service

@Service
class MajorService(
    getMajorService: GetMajorService
) : GetMajorService by getMajorService