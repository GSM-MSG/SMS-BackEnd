package team.msg.sms.persistence.student

import com.querydsl.core.BooleanBuilder
import com.querydsl.core.Tuple
import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.data.domain.PageRequest
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component
import team.msg.sms.domain.student.dto.req.FiltersRequestData
import team.msg.sms.domain.student.model.Student
import team.msg.sms.domain.student.spi.StudentPort
import team.msg.sms.domain.user.model.User
import team.msg.sms.persistence.major.entity.QMajorJpaEntity
import team.msg.sms.persistence.region.entity.QRegionJpaEntity
import team.msg.sms.persistence.student.entity.QStudentJpaEntity
import team.msg.sms.persistence.student.mapper.toDomain
import team.msg.sms.persistence.student.mapper.toDomainPageWithUserInfo
import team.msg.sms.persistence.student.mapper.toDomainWithUserInfo
import team.msg.sms.persistence.student.mapper.toEntity
import team.msg.sms.persistence.student.repository.StudentJpaRepository
import team.msg.sms.persistence.techstack.entity.QTechStackJpaEntity
import team.msg.sms.persistence.user.entity.QUserJpaEntity
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

    override fun queryStudentById(uuid: UUID): Student.StudentWithUserInfo? =
        studentJpaRepository.findByIdOrNull(uuid)?.toDomainWithUserInfo()

    override fun existsStudentById(uuid: UUID): Boolean =
        studentJpaRepository.existsById(uuid)

    override fun existsStudentByUser(user: User): Boolean =
        studentJpaRepository.existsByUser(user.toEntity())

    override fun queryStudentsWithPage(page: Int, size: Int): Student.StudentWithPageInfo =
        studentJpaRepository.findAll(PageRequest.of(page - 1, size)).toDomainPageWithUserInfo()

    override fun queryStudentByUserId(userId: UUID): Student.StudentWithUserInfo? =
        studentJpaRepository.findByUserId(userId)?.toDomainWithUserInfo()

    override fun queryStudentByUser(user: User): Student {
        val student = studentJpaRepository.findByUser(user = user.toEntity())
        return student.toDomain()
    }

    override fun searchStudent(filtersRequestData: FiltersRequestData) {
        val student = QStudentJpaEntity.studentJpaEntity
        val techStack = QTechStackJpaEntity.techStackJpaEntity
        val user = QUserJpaEntity.userJpaEntity

        queryFactory
            .select(
                student.id,
                student.user.name,
                student.major,
                student.profileImgUrl,
                techStack.stack,
            )
            .from(student)
            .leftJoin(techStack).on(student.eq(techStack.student))
            .where(eqGrade(filtersRequestData.grade))
            .fetch()
    }

    private fun eqGrade(grades: List<Int>?): BooleanBuilder? {
        if(grades == null) return null

        val booleanBuilder = BooleanBuilder()
        for(grade in grades) {
            booleanBuilder.or(QUserJpaEntity.userJpaEntity.stuNum.substring(0, 1).eq(grade.toString()))
        }

        return booleanBuilder
    }
}