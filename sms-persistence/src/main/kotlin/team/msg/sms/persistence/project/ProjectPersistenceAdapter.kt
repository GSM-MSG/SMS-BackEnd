package team.msg.sms.persistence.project

import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component
import team.msg.sms.domain.project.model.Project
import team.msg.sms.domain.project.spi.ProjectPort
import team.msg.sms.domain.student.exception.StudentNotFoundException
import team.msg.sms.domain.student.model.Student
import team.msg.sms.persistence.project.mapper.toDomain
import team.msg.sms.persistence.project.mapper.toEntity
import team.msg.sms.persistence.project.repository.ProjectJpaRepository
import team.msg.sms.persistence.student.repository.StudentJpaRepository
import java.util.*

@Component
class ProjectPersistenceAdapter(
    val projectRepository: ProjectJpaRepository,
    val studentRepository: StudentJpaRepository,
) : ProjectPort {
    override fun save(project: Project): Project {
        val student = studentRepository.findByIdOrNull(project.studentId) ?: throw StudentNotFoundException
        return projectRepository.save(project.toEntity(student)).toDomain()
    }

    override fun deleteAllByStudent(student: Student) {
        val student = studentRepository.findByIdOrNull(student.id) ?: throw StudentNotFoundException
        val project = projectRepository.findAllByStudent(student)
        projectRepository.deleteAll(project)
    }

    override fun deleteByProject(project: Project, student: Student) {
        val student = studentRepository.findByIdOrNull(student.id) ?: throw StudentNotFoundException
        projectRepository.delete(project.toEntity(student))
    }

    override fun queryAllProjectByStudentId(studentId: UUID): List<Project> {
        val student = studentRepository.findByIdOrNull(studentId)
            ?: throw StudentNotFoundException
        return projectRepository.findAllByStudent(
            student = student
        ).map {
            it.toDomain()
        }
    }
}