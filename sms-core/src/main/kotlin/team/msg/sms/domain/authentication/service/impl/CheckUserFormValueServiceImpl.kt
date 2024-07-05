package team.msg.sms.domain.authentication.service.impl

import team.msg.sms.common.annotation.Service
import team.msg.sms.domain.authentication.service.CheckUserFormValueService
import team.msg.sms.domain.authentication.spi.UserFormValuePort
import java.util.*

@Service
class CheckUserFormValueServiceImpl(
    private val userFormValuePort: UserFormValuePort
) : CheckUserFormValueService{
    override fun checkUserFormValueBySetIds(setIds: List<UUID>): Boolean =
        userFormValuePort.existsUserFormValueBySetIds(setIds)

}