package team.msg.sms.domain.authentication.service

import team.msg.sms.common.annotation.Service

@Service
class SelectorSectionValueService(
    getSelectorSectionValueService: GetSelectorSectionValueService,
    commandSelectorSectionValueService: CommandSelectorSectionValueService
) : GetSelectorSectionValueService by getSelectorSectionValueService,
    CommandSelectorSectionValueService by commandSelectorSectionValueService