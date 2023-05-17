package team.msg.sms.persistence.major.entity

import team.msg.sms.persistence.BaseIdEntity
import javax.persistence.Entity
import javax.persistence.Table

@Entity
@Table(name = "major")
class MajorJpaEntity(
    val major: String
) : BaseIdEntity()