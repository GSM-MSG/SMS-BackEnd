package team.msg.sms.persistence.student

import org.springframework.data.domain.PageRequest
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component
import team.msg.sms.domain.student.model.Student
import team.msg.sms.domain.student.spi.StudentPort
import team.msg.sms.domain.user.model.User
import team.msg.sms.persistence.student.mapper.toDomain
import team.msg.sms.persistence.student.mapper.toDomainPageWithUserInfo
import team.msg.sms.persistence.student.mapper.toDomainWithUserInfo
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

    override fun deleteById(studentId: UUID) =
        studentJpaRepository.deleteById(studentId)

    override fun queryStudentById(uuid: UUID): Student.StudentWithUserInfo? =
        studentJpaRepository.findByIdOrNull(uuid)?.toDomainWithUserInfo()

    override fun existsStudentById(uuid: UUID): Boolean =
        studentJpaRepository.existsById(uuid)

    override fun existsStudentByUser(user: User): Boolean =
        studentJpaRepository.existsByUser(user.toEntity())

    override fun getStudentsWithPage(page: Int, size: Int): Student.StudentWithPageInfo =
        studentJpaRepository.findAll(PageRequest.of(page - 1, size)).toDomainPageWithUserInfo()

    override fun getStudentByUserId(userId: UUID): Student =
        studentJpaRepository.findByUserId(userId).toDomain()

    override fun queryStudentByUser(user: User): Student =
        studentJpaRepository.findByUser(user = user.toEntity()).toDomain()
}