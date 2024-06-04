package team.msg.sms.persistence.file.entity

import org.hibernate.annotations.GenericGenerator
import team.msg.sms.domain.file.model.FileType
import team.msg.sms.persistence.BaseUuidEntity
import java.util.UUID
import javax.persistence.*

@Entity
@Table(name = "file")
class FileJpaEntity(
    override val id: UUID,

    val fileName: String,

    val fileUrl: String,

    @Enumerated(EnumType.STRING)
    val fileType: FileType,

    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(columnDefinition = "BINARY(16)", nullable = false)
    val targetId: UUID
): BaseUuidEntity(id)