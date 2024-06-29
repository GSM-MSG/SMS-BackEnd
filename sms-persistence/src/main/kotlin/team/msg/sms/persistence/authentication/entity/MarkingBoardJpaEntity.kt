package team.msg.sms.persistence.authentication.entity

import org.hibernate.annotations.GenericGenerator
import team.msg.sms.domain.authentication.model.MarkingBoardType
import team.msg.sms.persistence.BaseUuidEntity
import java.util.*
import javax.persistence.*

@Entity(name = "marking_board")
class MarkingBoardJpaEntity(
    override val id: UUID,

    val title: String,

    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(columnDefinition = "BINARY(16)", nullable = false)
    val authenticationId: UUID,

    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(columnDefinition = "BINARY(16)", nullable = false)
    val studentId: UUID,

    val totalScore: Double,

    @Enumerated(EnumType.STRING)
    val markingBoardType : MarkingBoardType
): BaseUuidEntity(id)