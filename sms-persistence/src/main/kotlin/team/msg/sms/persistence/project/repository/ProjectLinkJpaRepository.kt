package team.msg.sms.persistence.project.repository

import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import team.msg.sms.persistence.project.entity.ProjectLinkJpaEntity

@Repository
interface ProjectLinkJpaRepository : CrudRepository<ProjectLinkJpaEntity, Long> {
    fun findAllByProjectId(projectId: Long): List<ProjectLinkJpaEntity>
    @Modifying
    @Query("delete from ProjectLinkJpaEntity pt where pt.project.id in :projects")
    fun deleteAllByProjects(@Param("projects") projects: List<Long>)
}