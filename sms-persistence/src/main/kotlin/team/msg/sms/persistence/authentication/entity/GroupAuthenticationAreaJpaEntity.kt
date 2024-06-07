package team.msg.sms.persistence.authentication.entity

import org.hibernate.annotations.GenericGenerator
import team.msg.sms.persistence.BaseUuidEntity
import java.util.*
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Table

@Entity
@Table(name = "group_authentication_area")
class GroupAuthenticationAreaJpaEntity(
    override val id: UUID,

    val title: String,

    val sort: Int,

    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(columnDefinition = "BINARY(16)")
    val authenticationFormId: UUID
) : BaseUuidEntity(id)