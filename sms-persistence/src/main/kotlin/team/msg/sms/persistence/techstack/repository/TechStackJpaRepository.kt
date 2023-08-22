package team.msg.sms.persistence.techstack.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import team.msg.sms.persistence.techstack.entity.TechStackJpaEntity

@Repository
interface TechStackJpaRepository : JpaRepository<TechStackJpaEntity, Long> {
    @Query("select techStack from TechStackJpaEntity techStack where techStack.count >= 2 and techStack.stack like concat(:stack, '%')")
    fun findByStackStartingWithCountGreaterThanTwo(@Param("stack") stack: String): List<TechStackJpaEntity>

    @Query("select techStack from TechStackJpaEntity techStack where techStack.count >= 2")
    fun findAllWithCountGreaterThanTwo(): List<TechStackJpaEntity>

}