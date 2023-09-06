package team.msg.sms.persistence.project.entity

import team.msg.sms.persistence.BaseIdEntity
import javax.persistence.*

@Entity
@Table(name = "project_link")
class ProjectLinkJpaEntity(
    @Column(length = 50)
    val name: String,

    @Column(length = 5000)
    val url: String,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id")
    val project: ProjectJpaEntity,
) : BaseIdEntity()