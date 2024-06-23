package team.msg.sms.persistence.user.entity

import team.msg.sms.persistence.BaseIdEntity
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne

@Entity(name = "user_form")
class UserFcmJpaEntity(
    override val id: Long,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    val user: UserJpaEntity,

    val token: String
): BaseIdEntity(id)