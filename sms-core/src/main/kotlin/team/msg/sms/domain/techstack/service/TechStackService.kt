package team.msg.sms.domain.techstack.service

import team.msg.sms.common.annotation.Service

@Service
class TechStackService(
    commandTechStackService: CommandTechStackService,
    queryTechStackService: QueryTechStackService
) : CommandTechStackService by commandTechStackService,
        QueryTechStackService by queryTechStackService