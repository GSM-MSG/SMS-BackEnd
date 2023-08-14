package team.msg.sms.domain.project.service

import team.msg.sms.common.annotation.Service

@Service
class ProjectService(
    commandProjectService: CommandProjectService,
    getProjectService: GetProjectService
) : CommandProjectService by commandProjectService,
    GetProjectService by getProjectService