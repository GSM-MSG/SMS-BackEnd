package team.msg.sms.persistence.region.entity

import team.msg.sms.persistence.BaseIdEntity
import team.msg.sms.persistence.student.entity.StudentJpaEntity
import javax.persistence.*

@Entity
@Table(name = "region")
class RegionJpaEntity(
    @Column
    val region: String,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id")
    val student: StudentJpaEntity
) : BaseIdEntity()