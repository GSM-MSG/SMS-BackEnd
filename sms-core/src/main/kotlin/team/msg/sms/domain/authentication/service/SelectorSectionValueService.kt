package team.msg.sms.domain.authentication.service

import team.msg.sms.common.annotation.Service

@Service
class SelectorSectionValueService(
    getSelectorSectionValueService: GetSelectorSectionValueService
) : GetSelectorSectionValueService by getSelectorSectionValueService