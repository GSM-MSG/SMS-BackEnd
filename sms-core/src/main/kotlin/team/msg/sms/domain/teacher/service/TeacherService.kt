package team.msg.sms.domain.teacher.service

import team.msg.sms.common.annotation.Service

@Service
class TeacherService (
    checkTeacherService: CheckTeacherService
) : CheckTeacherService by checkTeacherService