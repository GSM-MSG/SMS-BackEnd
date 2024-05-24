package team.msg.sms.domain.teacher.service

import team.msg.sms.common.annotation.Service

@Service
class HomeroomTeacherService (
    checkHomeroomTeacherService: CheckHomeroomTeacherService,
    commandHomeroomTeacherService: CommandHomeroomTeacherService
) : CheckHomeroomTeacherService by checkHomeroomTeacherService,
    CommandHomeroomTeacherService by commandHomeroomTeacherService