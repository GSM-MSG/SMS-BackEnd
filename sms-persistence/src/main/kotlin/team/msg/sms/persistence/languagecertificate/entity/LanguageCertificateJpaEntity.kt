package team.msg.sms.persistence.languagecertificate.entity

import team.msg.sms.persistence.BaseIdEntity
import team.msg.sms.persistence.student.entity.StudentJpaEntity
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.Table

@Entity
@Table(name = "language_certificate")
class LanguageCertificateJpaEntity(
    override val id: Long,

    val languageCertificateName: String,

    val score: String,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id")
    val student: StudentJpaEntity
) : BaseIdEntity()