package team.msg.sms.persistence.authentication.entity

import team.msg.sms.persistence.BaseUuidEntity
import java.util.*
import javax.persistence.Entity
import javax.persistence.Table

@Entity
@Table(name = "authentication_form")
class AuthenticationFormJpaEntity(
    override val id: UUID,

    val title: String,

    val version: String,
) : BaseUuidEntity(id)