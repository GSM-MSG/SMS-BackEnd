package team.msg.sms.persistence.authentication.entity

import team.msg.sms.persistence.BaseUuidEntity
import java.util.*
import javax.persistence.Column
import javax.persistence.Entity

@Entity(name = "authentication_field_group")
class AuthenticationFieldGroupJpaEntity(
    override val id: UUID,

    val maxScore: Double,

    @Column(columnDefinition = "BINARY(16)", nullable = false)
    val sectionId: UUID,
): BaseUuidEntity(id)