package team.msg.sms.persistence.authentication.entity

import org.hibernate.annotations.GenericGenerator
import team.msg.sms.domain.authentication.model.FieldType
import team.msg.sms.persistence.BaseUuidEntity
import java.time.LocalDateTime
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "user_form_value")
class UserFormValueJpaEntity(
    override val id: UUID,

    @Column(columnDefinition = "BINARY(16)")
    val setId: UUID?,

    @Column(columnDefinition = "BINARY(16)", nullable = false)
    val authenticationSectionId: UUID,

    @Column(columnDefinition = "BINARY(16)", nullable = false)
    val authenticationFieldGroupId: UUID,

    @Column(columnDefinition = "BINARY(16)", nullable = false)
    val authenticationFieldId:UUID,

    val value: String?,

    @Enumerated(EnumType.STRING)
    val fieldType: FieldType,

    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(columnDefinition = "BINARY(16)")
    val targetId: UUID?,

    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(columnDefinition = "BINARY(16)")
    val createdBy: UUID,

    val createdAt: LocalDateTime,

    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(columnDefinition = "BINARY(16)")
    val authenticationFormId: UUID
) : BaseUuidEntity(id)