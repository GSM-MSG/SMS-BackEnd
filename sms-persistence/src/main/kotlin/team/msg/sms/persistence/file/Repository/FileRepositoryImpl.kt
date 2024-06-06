package team.msg.sms.persistence.file.Repository

import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.stereotype.Repository
import team.msg.sms.domain.file.model.FileType
import team.msg.sms.persistence.file.entity.FileJpaEntity
import team.msg.sms.persistence.file.entity.QFileJpaEntity
import java.util.*

@Repository
class FileRepositoryImpl(
    private val jpaQueryFactory: JPAQueryFactory
) : FileRepositoryCustom {

    override fun findAllByTargetIdsAndTypeEqualsAuthentication(targetIds: List<UUID>): List<FileJpaEntity> {
        val qFile = QFileJpaEntity.fileJpaEntity
        return jpaQueryFactory
            .selectFrom(qFile)
            .where(
                qFile.targetId.`in`(targetIds)
                    .and(qFile.fileType.eq(FileType.AUTHENTICATION))
            )
            .fetch()
            .toList()
    }
}