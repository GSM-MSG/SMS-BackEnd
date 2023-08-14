package team.msg.sms.persistence.project.entity

import team.msg.sms.persistence.BaseIdEntity
import javax.persistence.*

@Entity
@Table(name = "project_link")
class ProjectLinkJpaEntity(
    @Column
    val name: String,

    @Column
    val url: String,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id")
    val project: ProjectJpaEntity,
) : BaseIdEntity()