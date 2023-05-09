package team.msg.sms.persistence.certificate.entity

import team.msg.sms.persistence.BaseIdEntity
import team.msg.sms.persistence.BaseUuidEntity
import team.msg.sms.persistence.student.entity.StudentJpaEntity
import java.util.UUID
import javax.persistence.*

@Entity
@Table(name = "certificate")
class CertificateJpaEntity(
    override val id: UUID,

    @Column
    val certificateName: String,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id")
    val student: StudentJpaEntity
) : BaseUuidEntity(id)