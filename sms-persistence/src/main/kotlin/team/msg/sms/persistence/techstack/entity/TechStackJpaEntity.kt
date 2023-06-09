package team.msg.sms.persistence.techstack.entity

import team.msg.sms.persistence.BaseIdEntity
import team.msg.sms.persistence.BaseUuidEntity
import team.msg.sms.persistence.student.entity.StudentJpaEntity
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "tech_stack")
class TechStackJpaEntity(
    @Column
    val stack: String,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id")
    val student: StudentJpaEntity,
) : BaseIdEntity()