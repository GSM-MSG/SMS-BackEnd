package team.msg.sms.persistence.authentication.entity

import team.msg.sms.domain.authentication.model.SectionType
import team.msg.sms.persistence.BaseUuidEntity
import java.time.LocalDateTime
import java.util.*
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated

@Entity(name = "authentication_field")
class AuthenticationFieldJpaEntity(
    override val id: UUID,

    @Column(columnDefinition = "BINARY(16)", nullable = false)
    val sectionId: UUID,

    val description: String?,

    val placeHolder: String?,

    @Enumerated(EnumType.STRING)
    val fieldInputType: SectionType,

    val fieldScore: Double,

    val sort: Int
) : BaseUuidEntity(id)