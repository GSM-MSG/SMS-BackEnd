package team.msg.sms.domain.project.service

import team.msg.sms.common.annotation.Service

@Service
class ProjectService(
    commandProjectService: CommandProjectService,
    getProjectService: GetProjectService,
    checkProjectService: CheckProjectService
) : CommandProjectService by commandProjectService,
    GetProjectService by getProjectService,
    CheckProjectService by checkProjectService