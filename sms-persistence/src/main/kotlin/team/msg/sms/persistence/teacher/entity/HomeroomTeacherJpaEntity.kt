package team.msg.sms.persistence.teacher.entity

import team.msg.sms.persistence.BaseIdEntity
import javax.persistence.*

@Entity
@Table(name = "homeroom_teacher")
class HomeroomTeacherJpaEntity (
    override val id: Long,

    @Column
    val grade: Int,

    @Column(name = "class_num")
    val classNum: Int,

    @OneToOne(cascade = [CascadeType.ALL])
    @JoinColumn(name = "teacher_id")
    val teacher: TeacherJpaEntity
) : BaseIdEntity()