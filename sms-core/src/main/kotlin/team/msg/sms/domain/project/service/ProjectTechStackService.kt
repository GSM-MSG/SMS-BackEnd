package team.msg.sms.domain.project.service

import team.msg.sms.common.annotation.Service

@Service
class ProjectTechStackService(
    commandProjectTechStackService: CommandProjectTechStackService,
    getProjectTechStackService: GetProjectTechStackService
) : CommandProjectTechStackService by commandProjectTechStackService,
    GetProjectTechStackService by getProjectTechStackService