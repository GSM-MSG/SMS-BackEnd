package team.msg.sms.persistence.techstack

import org.springframework.stereotype.Component
import team.msg.sms.domain.student.model.Student
import team.msg.sms.domain.techstack.model.TechStack
import team.msg.sms.domain.techstack.spi.TechStackPort
import team.msg.sms.domain.user.model.User
import team.msg.sms.persistence.student.mapper.toEntity
import team.msg.sms.persistence.techstack.mapper.toDomain
import team.msg.sms.persistence.techstack.mapper.toEntity
import team.msg.sms.persistence.techstack.repository.TechStackJpaRepository
import team.msg.sms.persistence.user.mapper.toEntity

@Component
class TechStackPersistenceAdapter(
    private val techStackJpaRepository: TechStackJpaRepository,
) : TechStackPort {
    override fun saveAll(techStack: List<TechStack>, student: Student, user: User): List<TechStack> =
        techStackJpaRepository.saveAll(
            techStack
                .map { it.toEntity(student.toEntity(user.toEntity())) }
        )
            .map { it.toDomain() }

    override fun findAll(): List<TechStack> = techStackJpaRepository.findAll()
        .map { it.toDomain() }
}