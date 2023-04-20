package team.msg.sms.domain.user.service

import team.msg.sms.common.annotation.Service
import team.msg.sms.domain.user.spi.QueryUserPort

@Service
class CheckUserServiceImpl(
    private val queryUserPort: QueryUserPort
) : CheckUserService {
    override fun checkUserExistByEmail(email: String): Boolean =
        queryUserPort.existsUserByEmail(email)
}