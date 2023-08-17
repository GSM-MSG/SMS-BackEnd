package team.msg.sms.persistence.image.repository

import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.query.Param
import team.msg.sms.persistence.image.entity.ImageJpaEntity

interface ImageJpaRepository : CrudRepository<ImageJpaEntity, Long>{
    @Modifying
    @Query("delete from ImageJpaEntity pt where pt.project.id in :projects")
    fun deleteAllByProjects(@Param("projects") projects: List<Long>)

    fun findAllByProjectId(projectId: Long): List<ImageJpaEntity>
}