package team.msg.sms.persistence.certificate.entity

import team.msg.sms.persistence.BaseIdEntity
import team.msg.sms.persistence.student.entity.StudentJpaEntity
import javax.persistence.*

@Entity
@Table(name = "certificate")
class CertificateJpaEntity(
    @Column
    val certificateName: String,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id")
    val student: StudentJpaEntity
) : BaseIdEntity()