package team.msg.sms.persistence.student.entity

import team.msg.sms.domain.student.model.MilitaryService
import team.msg.sms.domain.student.model.WorkerType
import team.msg.sms.persistence.BaseIdEntity
import team.msg.sms.persistence.user.entity.UserJpaEntity
import javax.persistence.*

@Entity
@Table(name = "student")
class StudentJpaEntity(
    @Column
    val stuNum: String,

    @Column
    val department: String,

    @Column
    val contactNumber: String,

    @Column
    val contactEmail: String,

    @Column
    val major: String,

    @Column(nullable = true)
    val portfolioUrl: String?,

    @Enumerated(EnumType.STRING)
    @Column
    val workerType: WorkerType,

    @Column(nullable = true)
    val languageCertificate: String?,

    @Column
    val description: String,

    @Column
    val militaryService: MilitaryService,

    @Column
    val profileImgUrl: String,

    @OneToOne
    @JoinColumn(name = "user_id")
    val user: UserJpaEntity
) : BaseIdEntity()