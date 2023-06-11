package team.msg.sms.domain.student.service

import team.msg.sms.common.annotation.Service


@Service
class StudentService(
    commandStudentService: CommandStudentService,
    checkStudentService: CheckStudentService,
    getStudentService: GetStudentService,
    filterStudentService: FilterStudentService
) : CommandStudentService by commandStudentService,
     CheckStudentService by checkStudentService,
     GetStudentService by getStudentService,
     FilterStudentService by filterStudentService

