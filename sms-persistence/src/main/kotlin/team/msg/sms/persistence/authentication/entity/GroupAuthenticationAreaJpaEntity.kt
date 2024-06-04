package team.msg.sms.persistence.authentication.entity

import team.msg.sms.persistence.BaseUuidEntity
import java.util.*
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Table

@Entity
@Table(name = "group_authentication_area")
class GroupAuthenticationAreaJpaEntity(
    override val id: UUID,

    val title: String,

    val sort: Int
): BaseUuidEntity(id)