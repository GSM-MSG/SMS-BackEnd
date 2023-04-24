package team.msg.sms.persistence.student

import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component
import team.msg.sms.domain.student.model.Student
import team.msg.sms.domain.student.spi.QueryStudentPort
import team.msg.sms.persistence.student.mapper.toDomain
import team.msg.sms.persistence.student.repository.StudentJpaRepository
import java.util.*

@Component
class StudentPersistenceAdapter(
    private val studentJpaRepository: StudentJpaRepository
) : QueryStudentPort {

    override fun queryStudentById(uuid: UUID): Student? =
        studentJpaRepository.findByIdOrNull(uuid)?.toDomain()

    override fun existsStudentById(uuid: UUID): Boolean =
        studentJpaRepository.existsById(uuid)
}