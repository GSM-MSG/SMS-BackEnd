package team.msg.sms.persistence.techstack.entity

import team.msg.sms.persistence.BaseIdEntity
import javax.persistence.*

@Entity
@Table(name = "tech_stack")
class TechStackJpaEntity(
    override val id: Long,
    @Column
    val stack: String,
    @Column
    val count: Int
) : BaseIdEntity()