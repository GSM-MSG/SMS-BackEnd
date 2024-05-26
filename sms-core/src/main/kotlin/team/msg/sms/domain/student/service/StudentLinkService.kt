package team.msg.sms.domain.student.service

import team.msg.sms.common.annotation.Service

@Service
class StudentLinkService (
	commandStudentLinkService: CommandStudentLinkService,
	checkStudentLinkService: CheckStudentLinkService
): CommandStudentLinkService by commandStudentLinkService,
	CheckStudentLinkService by checkStudentLinkService