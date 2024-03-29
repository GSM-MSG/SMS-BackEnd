package team.msg.sms.persistence.prize

import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component
import team.msg.sms.domain.prize.model.Prize
import team.msg.sms.domain.prize.spi.PrizePort
import team.msg.sms.domain.student.exception.StudentNotFoundException
import team.msg.sms.domain.student.model.Student
import team.msg.sms.persistence.prize.entity.QPrizeJpaEntity
import team.msg.sms.persistence.prize.mapper.toDomain
import team.msg.sms.persistence.prize.mapper.toEntity
import team.msg.sms.persistence.prize.repository.PrizeJpaRepository
import team.msg.sms.persistence.student.repository.StudentJpaRepository
import java.util.*

@Component
class PrizePersistenceAdapter(
    private val prizeJpaRepository: PrizeJpaRepository,
    private val studentJpaRepository: StudentJpaRepository,
    private val jpaQueryFactory: JPAQueryFactory
) : PrizePort {
    override fun saveAll(prizes: List<Prize>) {
        val student = studentJpaRepository.findByIdOrNull(prizes.first().studentId)
            ?: throw StudentNotFoundException
        prizeJpaRepository.saveAll(
            prizes
                .map {
                    it.toEntity(student)
                }
        )
    }

    override fun deleteAllByStudent(student: Student) {
        val student = studentJpaRepository.findByIdOrNull(student.id)
            ?: throw StudentNotFoundException
        val prize = QPrizeJpaEntity.prizeJpaEntity
        jpaQueryFactory
            .delete(prize)
            .where(prize.student.id.eq(student.id))
            .execute()
    }

    override fun deleteByPrize(prize: Prize, student: Student) {
        val student = studentJpaRepository.findByIdOrNull(student.id)
            ?: throw StudentNotFoundException
        prizeJpaRepository.delete(prize.toEntity(student))
    }

    override fun queryAllPrizeByStudentId(studentId: UUID): List<Prize> {
        val student = studentJpaRepository.findByIdOrNull(studentId)
            ?: throw StudentNotFoundException
        return prizeJpaRepository.findAllByStudent(student)
            .map {
                it.toDomain()
            }
    }
}