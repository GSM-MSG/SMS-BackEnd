package team.msg.sms.persistence.techstack

import org.springframework.stereotype.Component
import team.msg.sms.domain.techstack.model.TechStack
import team.msg.sms.domain.techstack.spi.TechStackPort
import team.msg.sms.persistence.techstack.mapper.toDomain
import team.msg.sms.persistence.techstack.mapper.toEntity
import team.msg.sms.persistence.techstack.repository.TechStackJpaRepository

@Component
class TechStackPersistenceAdapter(
    private val techStackJpaRepository: TechStackJpaRepository
) : TechStackPort {
    override fun save(techStack: TechStack): TechStack =
        techStackJpaRepository.save(techStack.toEntity()).toDomain()

    override fun saveAll(techStack: List<TechStack>): List<TechStack> =
        techStackJpaRepository.saveAll(
            techStack
                .map { it.toEntity() }
        )
            .map { it.toDomain() }

    override fun queryAll(): List<TechStack> =
        techStackJpaRepository.findAll()
            .map {
                it.toDomain()
            }

    override fun queryAllByCount(): List<TechStack> =
        techStackJpaRepository.findAllByCountGreaterThan(2).map { it.toDomain() }

    override fun queryAllByStack(stack: String): List<TechStack> =
        techStackJpaRepository.findByStackStartingWith(stack).map { it.toDomain() }
}