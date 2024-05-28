package team.msg.sms.persistence.authentication.entity

import team.msg.sms.domain.authentication.model.ActivityStatus
import team.msg.sms.persistence.BaseUuidEntity
import team.msg.sms.persistence.student.entity.StudentJpaEntity
import java.util.UUID
import javax.persistence.*

@Entity
@Table(name = "authentication")
class AuthenticationJpaEntity (
    override val id: UUID,

    @Column
    val title: String,

    @Column
    val content: String,

    @ElementCollection
    @CollectionTable(
        name = "ActivityImages",
        joinColumns = [JoinColumn(name = "authentication_id")]
    )
    val activityImages: List<String>,

    @Column
    val score: Int,

    @Enumerated(EnumType.STRING)
    val activityStatus: ActivityStatus,

    @ManyToOne
    @JoinColumn(name = "student_id")
    val student: StudentJpaEntity
) : BaseUuidEntity(id)