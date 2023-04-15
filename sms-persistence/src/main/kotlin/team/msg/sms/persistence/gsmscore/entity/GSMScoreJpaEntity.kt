package team.msg.sms.persistence.gsmscore.entity

import team.msg.sms.persistence.BaseIdEntity
import team.msg.sms.persistence.student.entity.StudentJpaEntity
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.JoinColumn
import javax.persistence.OneToOne
import javax.persistence.Table

@Entity
@Table(name = "gsm_score")
class GSMScoreJpaEntity(
    @Column
    val score: Number,

    @Column
    val scoreFileUrl: String,

    @OneToOne
    @JoinColumn(name = "student_id")
    val student: StudentJpaEntity
) : BaseIdEntity()