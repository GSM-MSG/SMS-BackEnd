package team.msg.sms.domain.student.service

import team.msg.sms.common.annotation.Service


@Service
class StudentService(
    commandStudentService: CommandStudentService,
    checkStudentService: CheckStudentService
) : CommandStudentService by commandStudentService,
    CheckStudentService by checkStudentService
