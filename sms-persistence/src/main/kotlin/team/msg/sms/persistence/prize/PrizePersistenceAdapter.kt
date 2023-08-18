package team.msg.sms.persistence.prize

import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component
import team.msg.sms.domain.prize.model.Prize
import team.msg.sms.domain.prize.spi.PrizePort
import team.msg.sms.domain.student.exception.StudentNotFoundException
import team.msg.sms.domain.student.model.Student
import team.msg.sms.persistence.prize.mapper.toDomain
import team.msg.sms.persistence.prize.mapper.toEntity
import team.msg.sms.persistence.prize.repository.PrizeJpaRepository
import team.msg.sms.persistence.student.repository.StudentJpaRepository
import java.util.*

@Component
class PrizePersistenceAdapter(
    private val prizeJpaRepository: PrizeJpaRepository,
    private val studentJpaRepository: StudentJpaRepository
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
        prizeJpaRepository.deleteAllByStudent(student)
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