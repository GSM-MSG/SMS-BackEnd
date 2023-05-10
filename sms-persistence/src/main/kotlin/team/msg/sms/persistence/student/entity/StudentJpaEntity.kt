package team.msg.sms.persistence.student.entity

import team.msg.sms.domain.student.model.Department
import team.msg.sms.domain.student.model.FormOfEmployment
import team.msg.sms.domain.student.model.Major
import team.msg.sms.domain.student.model.MilitaryService
import team.msg.sms.persistence.BaseUuidEntity
import team.msg.sms.persistence.user.entity.UserJpaEntity
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "student")
class StudentJpaEntity(
    override val id: UUID,

    @Column
    val stuNum: String,

    @Enumerated(EnumType.STRING)
    @Column
    val department: Department,

    @Column
    val contactEmail: String,

    @Enumerated(EnumType.STRING)
    @Column
    val major: Major,

    @Column(nullable = true)
    val portfolioUrl: String?,

    @Enumerated(EnumType.STRING)
    @Column
    val formOfEmployment: FormOfEmployment,

    @Column
    val introduce: String,

    @Column
    val gsmAuthenticationScore: Int,

    @Column
    val salary: Int,

    @Enumerated(EnumType.STRING)
    @Column
    val militaryService: MilitaryService,

    @Column
    val profileImgUrl: String,

    @OneToOne
    @JoinColumn(name = "user_id")
    val user: UserJpaEntity
) : BaseUuidEntity(id)