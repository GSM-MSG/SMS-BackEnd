package team.msg.sms.domain.project.service

import team.msg.sms.common.annotation.Service

@Service
class ProjectLinkService(
    commandProjectLinkService: CommandProjectLinkService,
    getProjectLinkService: GetProjectLinkService,
    checkProjectLinkService: CheckProjectLinkService
) : CommandProjectLinkService by commandProjectLinkService,
    GetProjectLinkService by getProjectLinkService,
    CheckProjectLinkService by checkProjectLinkService