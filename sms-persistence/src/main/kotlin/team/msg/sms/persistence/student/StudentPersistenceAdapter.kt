package team.msg.sms.persistence.student

import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component
import team.msg.sms.domain.student.model.Student
import team.msg.sms.domain.student.spi.StudentPort
import team.msg.sms.domain.user.model.User
import team.msg.sms.persistence.student.mapper.toDomain
import team.msg.sms.persistence.student.mapper.toEntity
import team.msg.sms.persistence.student.repository.StudentJpaRepository
import team.msg.sms.persistence.user.mapper.toEntity
import java.util.*

@Component
class StudentPersistenceAdapter(
    private val studentJpaRepository: StudentJpaRepository
) : StudentPort {
    override fun saveStudent(student: Student, user: User): Student =
        studentJpaRepository
            .save(student.toEntity(user.toEntity()))
            .toDomain()

    override fun queryStudentById(uuid: UUID): Student? =
        studentJpaRepository.findByIdOrNull(uuid)?.toDomain()

    override fun existsStudentById(uuid: UUID): Boolean =
        studentJpaRepository.existsById(uuid)

    override fun existsStudentByUser(user: User): Boolean =
        studentJpaRepository.existsByUser(user.toEntity())

}