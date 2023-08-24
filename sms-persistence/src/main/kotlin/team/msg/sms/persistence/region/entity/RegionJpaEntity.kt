package team.msg.sms.persistence.region.entity

import team.msg.sms.persistence.BaseIdEntity
import team.msg.sms.persistence.BaseUuidEntity
import team.msg.sms.persistence.student.entity.StudentJpaEntity
import java.util.UUID
import javax.persistence.*

@Entity
@Table(name = "region")
class RegionJpaEntity(
    override val id: Long,

    @Column
    val region: String,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id")
    val student: StudentJpaEntity
) : BaseIdEntity()