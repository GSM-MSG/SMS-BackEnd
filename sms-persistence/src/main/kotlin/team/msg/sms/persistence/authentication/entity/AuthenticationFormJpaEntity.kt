package team.msg.sms.persistence.authentication.entity

import org.hibernate.annotations.GenericGenerator
import team.msg.sms.persistence.BaseUuidEntity
import java.time.LocalDateTime
import java.util.*
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Table

@Entity
@Table(name = "authentication_form")
class AuthenticationFormJpaEntity(
    override val id: UUID,

    val title: String,

    val version: String,

    val active: Boolean,

    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(columnDefinition = "BINARY(16)", nullable = false)
    val createdBy: UUID,

    val createdAt: LocalDateTime
) : BaseUuidEntity(id)