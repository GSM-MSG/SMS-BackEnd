package team.msg.sms.domain.authentication.spi

import team.msg.sms.domain.authentication.model.UserFormValue

interface CommandUserFormValuePort {
    fun saveAll(userFormValueList: List<UserFormValue>): List<UserFormValue>
}