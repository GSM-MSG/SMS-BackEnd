package team.msg.sms.domain.user.model

import team.msg.sms.common.annotation.Aggregate
import team.msg.sms.domain.auth.model.Role

@Aggregate
data class User(
    val id: Long,
    val email: String,
    val roles: MutableList<Role>
)