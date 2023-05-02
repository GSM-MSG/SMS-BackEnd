package team.msg.sms.persistence.languagecertificate.entity

import team.msg.sms.persistence.BaseIdEntity
import team.msg.sms.persistence.student.entity.StudentJpaEntity
import javax.persistence.FetchType
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne

class LanguageCertificateJpaEntity(
    val languageCertificateName: String,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id")
    val student: StudentJpaEntity
) : BaseIdEntity()