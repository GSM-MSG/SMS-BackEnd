package team.msg.sms.persistence.student

import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.data.domain.PageRequest
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component
import team.msg.sms.domain.student.model.Student
import team.msg.sms.domain.student.spi.StudentPort
import team.msg.sms.domain.user.model.User
import team.msg.sms.persistence.student.entity.QStudentJpaEntity
import team.msg.sms.persistence.student.mapper.toDomain
import team.msg.sms.persistence.student.mapper.toDomainPageWithUserInfo
import team.msg.sms.persistence.student.mapper.toDomainWithUserInfo
import team.msg.sms.persistence.student.mapper.toEntity
import team.msg.sms.persistence.student.repository.StudentJpaRepository
import team.msg.sms.persistence.user.mapper.toEntity
import java.util.*

@Component
class StudentPersistenceAdapter(
    private val studentJpaRepository: StudentJpaRepository,
    private val queryFactory: JPAQueryFactory
) : StudentPort {
    override fun saveStudent(student: Student, user: User): Student =
        studentJpaRepository
            .save(student.toEntity(user.toEntity()))
            .toDomain()

    override fun deleteById(studentId: UUID) =
        studentJpaRepository.deleteById(studentId)

    override fun queryStudentWithUserInfoById(uuid: UUID): Student.StudentWithUserInfo? =
        studentJpaRepository.findByIdOrNull(uuid)?.toDomainWithUserInfo()

    override fun queryStudentById(uuid: UUID): Student? =
        studentJpaRepository.findByIdOrNull(uuid)?.toDomain()

    override fun existsStudentById(uuid: UUID): Boolean =
        studentJpaRepository.existsById(uuid)

    override fun existsStudentByUser(user: User): Boolean =
        studentJpaRepository.existsByUser(user.toEntity())

    override fun queryStudentsWithPage(page: Int, size: Int): Student.StudentWithPageInfo =
        studentJpaRepository.findAll(PageRequest.of(page - 1, size)).toDomainPageWithUserInfo()

    override fun queryStudentIds(): List<UUID> {
        val qStudent = QStudentJpaEntity.studentJpaEntity

        return queryFactory
            .select(qStudent.id)
            .from(qStudent)
            .fetch()
    }

    override fun queryStudentByUserId(userId: UUID): Student =
        studentJpaRepository.findByUserId(userId)!!.toDomain()

    override fun queryStudentUserInfoByUserId(userId: UUID): Student.StudentWithUserInfo? =
        studentJpaRepository.findByUserId(userId)?.toDomainWithUserInfo()

    override fun queryStudentByUser(user: User): Student {
        val student = studentJpaRepository.findByUser(user = user.toEntity())
        return student.toDomain()
    }

    override fun searchStudent(): List<Student.StudentWithUserInfo> {
        val student = QStudentJpaEntity.studentJpaEntity

        return queryFactory
            .selectFrom(student)
            .fetch()
            .map { it.toDomainWithUserInfo() }
    }
}