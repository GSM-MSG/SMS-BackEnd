package team.msg.sms.persistence.image.entity

import team.msg.sms.persistence.BaseIdEntity
import team.msg.sms.persistence.project.entity.ProjectJpaEntity
import javax.persistence.*

@Entity
@Table(name = "image")
class ImageJpaEntity(
    @Column(length = 1000)
    val imageUrl: String,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id")
    val project: ProjectJpaEntity
) : BaseIdEntity()