package team.msg.sms.persistence.student

import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component
import team.msg.sms.domain.student.exception.StudentNotFoundException
import team.msg.sms.domain.student.model.Student
import team.msg.sms.domain.student.model.StudentTechStack
import team.msg.sms.domain.student.spi.StudentTechStackPort
import team.msg.sms.persistence.student.mapper.toDomain
import team.msg.sms.persistence.student.mapper.toEntity
import team.msg.sms.persistence.student.repository.StudentJpaRepository
import team.msg.sms.persistence.student.repository.StudentTechStackJpaRepository
import team.msg.sms.persistence.techstack.repository.TechStackJpaRepository
import java.util.*

@Component
class StudentTechStackPersistenceAdapter(
    private val studentTechStackJpaRepository: StudentTechStackJpaRepository,
    private val studentJpaRepository: StudentJpaRepository,
    private val techStackJpaRepository: TechStackJpaRepository
) : StudentTechStackPort {
    override fun save(studentTechStack: StudentTechStack) {
        val student = studentJpaRepository.findByIdOrNull(studentTechStack.studentId)
            ?: throw StudentNotFoundException
        val techStack = techStackJpaRepository.findByIdOrNull(studentTechStack.techStackId)
        studentTechStackJpaRepository.save(
            studentTechStack.toEntity(
                studentJpaEntity = student,
                techStackJpaEntity = techStack!!
            )
        )
    }

    override fun deleteAllByStudent(student: Student) {
        val student = studentJpaRepository.findByIdOrNull(student.id)
            ?: throw StudentNotFoundException
        studentTechStackJpaRepository.deleteAllByStudent(student)
    }

    override fun queryStudentTechStackByStudentId(studentId: UUID): List<StudentTechStack> =
        studentTechStackJpaRepository.findAllByStudentId(studentId = studentId)
            .map {
                it.toDomain()
            }
}