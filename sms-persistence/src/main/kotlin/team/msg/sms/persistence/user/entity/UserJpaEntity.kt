package team.msg.sms.persistence.user.entity

import org.hibernate.annotations.ColumnDefault
import team.msg.sms.domain.auth.model.Role
import team.msg.sms.persistence.BaseIdEntity
import team.msg.sms.persistence.BaseUuidEntity
import java.util.UUID
import javax.persistence.*

@Entity
@Table(name = "user")
class UserJpaEntity(
    override val id: UUID,

    @Column(columnDefinition = "VARCHAR(255)")
    val email: String,

    @Column
    val name: String,

    @Column
    val stuNum: String,

    @Enumerated(EnumType.STRING)
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "Role", joinColumns = [JoinColumn(name = "user_id")])
    val roles: MutableList<Role> = mutableListOf()
) : BaseUuidEntity(id) {
}