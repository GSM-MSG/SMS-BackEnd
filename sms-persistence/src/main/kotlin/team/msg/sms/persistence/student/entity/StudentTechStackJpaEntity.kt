package team.msg.sms.persistence.student.entity

import team.msg.sms.persistence.BaseIdEntity
import team.msg.sms.persistence.techstack.entity.TechStackJpaEntity
import javax.persistence.*

@Entity
@Table(name = "student_tech_stack")
class StudentTechStackJpaEntity(
    @OneToOne
    @JoinColumn(name = "student_id")
    val student: StudentJpaEntity,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tech_stack_id")
    val techStack: TechStackJpaEntity
) : BaseIdEntity()