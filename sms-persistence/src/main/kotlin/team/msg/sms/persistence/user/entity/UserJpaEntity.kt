package team.msg.sms.persistence.user.entity

import team.msg.sms.domain.auth.model.Role
import team.msg.sms.persistence.BaseIdEntity
import javax.persistence.*

@Entity
@Table(name = "user")
class UserJpaEntity(
    @Column(columnDefinition = "VARCHAR(255)")
    val email: String,

    @Column
    val name: String,

    @Enumerated(EnumType.STRING)
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "Role", joinColumns = [JoinColumn(name = "user_id")])
    val roles: MutableList<Role> = mutableListOf()
) : BaseIdEntity()