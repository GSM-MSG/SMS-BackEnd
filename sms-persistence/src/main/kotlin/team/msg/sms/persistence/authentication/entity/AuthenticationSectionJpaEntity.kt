package team.msg.sms.persistence.authentication.entity

import org.hibernate.annotations.GenericGenerator
import team.msg.sms.domain.authentication.model.SectionType
import team.msg.sms.persistence.BaseUuidEntity
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "authentication_section")
class AuthenticationSectionJpaEntity(
    override val id: UUID,

    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(columnDefinition = "BINARY(16)", nullable = false)
    val groupId: UUID,

    val sectionName: String,

    val description: String,

    val placeHolder: String,

    @Enumerated(EnumType.STRING)
    val sectionType: SectionType,

    val sectionScore:  Int,

    val maxCount: Int,
): BaseUuidEntity(id)