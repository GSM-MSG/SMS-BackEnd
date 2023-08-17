package team.msg.sms.persistence.project.entity

import team.msg.sms.persistence.BaseIdEntity
import team.msg.sms.persistence.techstack.entity.TechStackJpaEntity
import javax.persistence.CascadeType
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.Table

@Entity
@Table(name = "project_tech_stack")
class ProjectTechStackJpaEntity(
    @ManyToOne(cascade = [CascadeType.ALL])
    @JoinColumn(name = "project_id")
    val project: ProjectJpaEntity,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tech_stack_id")
    val techStack: TechStackJpaEntity
) : BaseIdEntity()