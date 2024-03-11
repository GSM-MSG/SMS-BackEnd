package team.msg.sms.domain.teacher.service

import team.msg.sms.common.annotation.Service

@Service
class TeacherService (
    checkTeacherService: CheckTeacherService,
    commandTeacherService: CommandTeacherService,
	getTeacherService: GetTeacherService
) : CheckTeacherService by checkTeacherService,
    CommandTeacherService by commandTeacherService,
	GetTeacherService by getTeacherService