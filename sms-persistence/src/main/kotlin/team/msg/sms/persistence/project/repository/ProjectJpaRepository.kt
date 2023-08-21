package team.msg.sms.persistence.project.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import team.msg.sms.persistence.project.entity.ProjectJpaEntity
import team.msg.sms.persistence.student.entity.StudentJpaEntity

@Repository
interface ProjectJpaRepository : JpaRepository<ProjectJpaEntity, Long> {
    fun save(projectJpaEntity: ProjectJpaEntity): ProjectJpaEntity
    fun findAllByStudent(student: StudentJpaEntity): List<ProjectJpaEntity>
}