package team.msg.sms.persistence.authentication.entity

import org.springframework.data.annotation.CreatedDate
import team.msg.sms.domain.authentication.model.ActivityStatus
import team.msg.sms.persistence.BaseIdEntity
import team.msg.sms.persistence.teacher.entity.TeacherJpaEntity
import java.time.LocalDateTime
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
    val authentication: AuthenticationJpaEntity,

    @CreatedDate
    val createdAt: LocalDateTime
) : BaseIdEntity()