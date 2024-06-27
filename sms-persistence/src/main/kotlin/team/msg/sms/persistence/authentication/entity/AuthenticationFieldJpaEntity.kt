package team.msg.sms.persistence.authentication.entity

import team.msg.sms.domain.authentication.model.FieldType
import team.msg.sms.persistence.BaseUuidEntity
import java.util.*
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated

@Entity(name = "authentication_field")
class AuthenticationFieldJpaEntity(
    override val id: UUID,

    val description: String?,

    @Column(columnDefinition = "BINARY(16)", nullable = false)
    val groupId: UUID,

    val placeHolder: String?,

    @Enumerated(EnumType.STRING)
    val fieldInputType: FieldType,

    val sort: Int
) : BaseUuidEntity(id)