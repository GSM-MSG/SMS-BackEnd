package team.msg.sms.domain.student.service

import team.msg.sms.common.annotation.Service

@Service
class StudentTechStackService(
    commandStudentTechStackService: CommandStudentTechStackService,
    getStudentTechStackService: GetStudentTechStackService,
    checkStudentTechStackService: CheckStudentTechStackService
) : CommandStudentTechStackService by commandStudentTechStackService,
    GetStudentTechStackService by getStudentTechStackService,
    CheckStudentTechStackService by checkStudentTechStackService