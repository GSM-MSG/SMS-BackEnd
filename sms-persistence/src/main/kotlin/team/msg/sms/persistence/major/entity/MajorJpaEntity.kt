package team.msg.sms.persistence.major.entity

import team.msg.sms.persistence.BaseIdEntity
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Table

@Entity
@Table(name = "major")
class MajorJpaEntity(
    @Column
    val major: String,

    @Column
    val isDefaultMajor: Boolean
) : BaseIdEntity(){
}