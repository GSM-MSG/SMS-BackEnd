package team.msg.sms.domain.user.model

import team.msg.sms.common.annotation.Aggregate
import team.msg.sms.domain.auth.model.Role
import java.util.UUID

@Aggregate
data class User(
    val id: UUID = UUID.randomUUID(),
    val name: String,
    val email: String,
    val stuNum: String,
    val roles: MutableList<Role>
)