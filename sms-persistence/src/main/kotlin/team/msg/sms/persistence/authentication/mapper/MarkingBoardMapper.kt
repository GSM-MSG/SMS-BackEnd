package team.msg.sms.persistence.authentication.mapper

import team.msg.sms.domain.authentication.model.MarkingBoard
import team.msg.sms.persistence.authentication.entity.MarkingBoardJpaEntity

fun MarkingBoardJpaEntity.toDomain() =
    MarkingBoard(
        id = id,
        authenticationId = authenticationId,
        studentId = studentId,
        title = title,
        totalScore = totalScore,
        markingBoardType = markingBoardType,
        graderName = graderName
    )

fun MarkingBoard.toEntity() =
    MarkingBoardJpaEntity(
        id = id,
        authenticationId = authenticationId,
        studentId = studentId,
        title = title,
        totalScore = totalScore,
        markingBoardType = markingBoardType,
        graderName = graderName
    )