package team.msg.sms.persistence.authentication.repository.queryDSL

import com.querydsl.core.annotations.QueryProjection
import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Repository
import team.msg.sms.domain.authentication.dto.res.UserBoardPageResponseData
import team.msg.sms.domain.authentication.dto.res.UserBoardWithStudentInfoResponseData
import team.msg.sms.domain.authentication.model.MarkingBoard
import team.msg.sms.domain.authentication.model.MarkingBoardType
import team.msg.sms.persistence.authentication.entity.MarkingBoardJpaEntity
import team.msg.sms.persistence.authentication.entity.QAuthenticationFormJpaEntity
import team.msg.sms.persistence.authentication.entity.QMarkingBoardJpaEntity
import team.msg.sms.persistence.student.entity.QStudentJpaEntity
import team.msg.sms.persistence.user.entity.QUserJpaEntity
import java.util.*

@Repository
class MarkingBoardRepositoryImpl(
    private val jpaQueryFactory: JPAQueryFactory
) : MarkingBoardCustomRepository {
    override fun findMarkingBoardWithStudentId(studentId: UUID): MarkingBoardJpaEntity? {
        val qMarkingBoard = QMarkingBoardJpaEntity.markingBoardJpaEntity
        val qAuthenticationForm = QAuthenticationFormJpaEntity.authenticationFormJpaEntity
        val data = jpaQueryFactory
            .select(qMarkingBoard)
            .from(qMarkingBoard)
            .join(qAuthenticationForm).on(qMarkingBoard.authenticationId.eq(qAuthenticationForm.id))
            .where(
                qMarkingBoard.studentId.eq(studentId)
                    .and(qAuthenticationForm.active.isTrue)
            )
            .fetch()
        return if (data.isEmpty()) null else {
            data.first()
        }
    }

    override fun findMarkingBoardWithStudentInfoByStudentIds(
        studentIds: List<UUID>,
        authenticationId: UUID,
        page: Int,
        size: Int
    ): UserBoardPageResponseData {
        return findMarkingBoardWithStudentInfoByStudentIdsAndType(
            studentIds = studentIds,
            authenticationId = authenticationId,
            page = page,
            size = size,
            types = null
        )
    }

    override fun findMarkingBoardWithStudentInfoByStudentIdsWithType(
        studentIds: List<UUID>,
        authenticationId: UUID,
        page: Int,
        size: Int,
        type: List<MarkingBoardType>
    ): UserBoardPageResponseData {
        return findMarkingBoardWithStudentInfoByStudentIdsAndType(
            studentIds = studentIds,
            authenticationId = authenticationId,
            page = page,
            size = size,
            types = type
        )
    }

    private fun findMarkingBoardWithStudentInfoByStudentIdsAndType(
        studentIds: List<UUID>,
        authenticationId: UUID,
        page: Int,
        size: Int,
        types: List<MarkingBoardType>?
    ): UserBoardPageResponseData {
        val qStudent = QStudentJpaEntity.studentJpaEntity
        val qUser = QUserJpaEntity.userJpaEntity
        val qMarkingBoard = QMarkingBoardJpaEntity.markingBoardJpaEntity

        val pageable = PageRequest.of(page - 1, size)

        val totalQuery = jpaQueryFactory.select(
            QMarkingBoardRepositoryImpl_UserBoardValueWithStudentInfoData(
                qMarkingBoard.id,
                qMarkingBoard.title,
                qMarkingBoard.markingBoardType,
                qStudent.id,
                qStudent.profileImgUrl,
                qMarkingBoard.totalScore,
                qUser.stuNum,
                qUser.name
            )
        )
            .from(qStudent)
            .leftJoin(qMarkingBoard)
            .on(qStudent.id.eq(qMarkingBoard.studentId).and(qMarkingBoard.authenticationId.eq(authenticationId)))
            .leftJoin(qUser).on(qStudent.user.id.eq(qUser.id))
            .where(
                qStudent.id.`in`(studentIds)
            )

        if (!types.isNullOrEmpty()) {
            if (types.contains(MarkingBoardType.NOT_SUBMITTED)) {
                totalQuery.where(qMarkingBoard.markingBoardType.`in`(types).or(qMarkingBoard.isNull))
            } else {
                totalQuery.where(qMarkingBoard.markingBoardType.`in`(types))
            }
        }

        val results = totalQuery
            .limit(size.toLong())
            .offset(pageable.offset)
            .fetch()

        val total = results.size.toLong()

        val pageImpl = PageImpl(results, pageable, total)

        return UserBoardPageResponseData(
            content = results.map { toResponse(it) },
            page = pageImpl.pageable.pageNumber + 1,
            contentSize = pageImpl.content.size,
            totalSize = pageImpl.totalElements,
            last = pageImpl.isLast
        )
    }


    data class UserBoardValueWithStudentInfoData @QueryProjection constructor(
        val id: UUID?,
        val title: String?,
        val type: MarkingBoardType?,
        val studentId: UUID,
        val profileImgUrl: String,
        val totalScore: Double,
        val studentNumber: String,
        val name: String
    )

    private fun toResponse(userBoard: UserBoardValueWithStudentInfoData): UserBoardWithStudentInfoResponseData {
        return UserBoardWithStudentInfoResponseData(
            id = userBoard.id,
            title = userBoard.title ?: "${userBoard.name} ${userBoard.studentNumber}",
            type = userBoard.type ?: MarkingBoardType.NOT_SUBMITTED,
            profileImgUrl = userBoard.profileImgUrl,
            totalScore = userBoard.totalScore,
            studentId = userBoard.studentId
        )
    }
}