package team.msg.sms.persistence.authentication.entity

import team.msg.sms.domain.authentication.model.ActivityStatus
import team.msg.sms.persistence.BaseIdEntity
import team.msg.sms.persistence.teacher.entity.TeacherJpaEntity
import javax.persistence.*

@Entity
@Table(name = "authentication_history")
class AuthenticationHistoryJpaEntity (
    override val id: Long,

    @Column
    val reason: String,

    @Enumerated(EnumType.STRING)
    val activityStatus: ActivityStatus,

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "teacher_id", nullable = true)
    val teacher: TeacherJpaEntity?,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "authentication_id")
    val authentication: AuthenticationJpaEntity
) : BaseIdEntity()