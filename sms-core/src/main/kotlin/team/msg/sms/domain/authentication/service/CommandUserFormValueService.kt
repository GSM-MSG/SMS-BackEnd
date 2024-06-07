package team.msg.sms.domain.authentication.service

import team.msg.sms.domain.authentication.model.UserFormValue

interface CommandUserFormValueService {
    fun save(userFormValue: UserFormValue): UserFormValue
}