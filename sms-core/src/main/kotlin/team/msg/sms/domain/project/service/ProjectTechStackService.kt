package team.msg.sms.domain.project.service

import team.msg.sms.common.annotation.Service

@Service
class ProjectTechStackService(
    commandProjectTechStackService: CommandProjectTechStackService,
    getProjectTechStackService: GetProjectTechStackService,
    checkProjectTechStackService: CheckProjectTechStackService
) : CommandProjectTechStackService by commandProjectTechStackService,
    GetProjectTechStackService by getProjectTechStackService,
    CheckProjectTechStackService by checkProjectTechStackService