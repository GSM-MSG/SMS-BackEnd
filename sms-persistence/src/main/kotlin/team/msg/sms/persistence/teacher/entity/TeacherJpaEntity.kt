package team.msg.sms.persistence.teacher.entity

import team.msg.sms.persistence.BaseUuidEntity
import team.msg.sms.persistence.user.entity.UserJpaEntity
import java.util.UUID
import javax.persistence.Entity
import javax.persistence.JoinColumn
import javax.persistence.OneToOne
import javax.persistence.Table

@Entity
@Table(name = "teacher")
class TeacherJpaEntity (
    override val id: UUID,

    @OneToOne
    @JoinColumn(name = "user_id")
    val user: UserJpaEntity
) : BaseUuidEntity(id)