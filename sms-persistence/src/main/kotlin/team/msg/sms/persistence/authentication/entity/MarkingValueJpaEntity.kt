package team.msg.sms.persistence.authentication.entity

import team.msg.sms.domain.authentication.model.MarkingType
import team.msg.sms.persistence.BaseUuidEntity
import java.time.LocalDateTime
import java.util.*
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated

@Entity(name = "marking_value")
class MarkingValueJpaEntity(
    override val id: UUID,

    val score: Double,

    @Enumerated(EnumType.STRING)
    val type : MarkingType,

    @Column(columnDefinition = "BINARY(16)", nullable = false)
    val fieldId: UUID,

    @Column(columnDefinition = "BINARY(16)", nullable = false)
    val groupId: UUID,

    @Column(columnDefinition = "BINARY(16)", nullable = false)
    val createdAt: LocalDateTime,

    @Column(columnDefinition = "BINARY(16)", nullable = false)
    val createdBy: UUID
): BaseUuidEntity(id)
