package team.msg.sms.domain.techstack.service

import team.msg.sms.common.annotation.Service

@Service
class TechStackService(
    commandTechStackService: CommandTechStackService,
    queryTechStackService: GetTechStackService
) : CommandTechStackService by commandTechStackService,
    GetTechStackService by queryTechStackService