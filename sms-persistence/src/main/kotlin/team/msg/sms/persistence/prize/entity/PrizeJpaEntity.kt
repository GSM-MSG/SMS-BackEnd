package team.msg.sms.persistence.prize.entity

import team.msg.sms.persistence.BaseIdEntity
import team.msg.sms.persistence.student.entity.StudentJpaEntity
import javax.persistence.*

@Entity
@Table(name = "prize")
class PrizeJpaEntity(
    override val id: Long,

    @Column(length = 40)
    val name: String,

    @Column(length = 20)
    val type: String,

    @Column
    val date: String,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id")
    val student: StudentJpaEntity
) : BaseIdEntity()