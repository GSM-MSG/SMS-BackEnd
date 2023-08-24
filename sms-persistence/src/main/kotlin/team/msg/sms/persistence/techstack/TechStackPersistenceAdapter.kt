package team.msg.sms.persistence.techstack

import org.springframework.stereotype.Component
import team.msg.sms.domain.techstack.exception.TechStackNotFoundException
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

    override fun deleteByTechStack(techStack: TechStack) =
        techStackJpaRepository.delete(techStack.toEntity())

    override fun decrementTechStackCount(techStack: TechStack) {
        val updatedCount = techStack.count - 1
        val updatedTechStack = techStack.copy(count = updatedCount)

        techStackJpaRepository.save(techStack.toEntity())
    }

    override fun queryAll(): List<TechStack> =
        techStackJpaRepository.findAll()
            .map {
                it.toDomain()
            }

    override fun queryAllByCount(): List<TechStack> =
        techStackJpaRepository.findAllWithCountGreaterThanTwo().map { it.toDomain() }

    override fun queryAllByStack(stack: String): List<TechStack> =
        techStackJpaRepository.findByStackStartingWithCountGreaterThanTwo(stack).map { it.toDomain() }

    override fun queryTechStackByStack(stack: String): TechStack =
        techStackJpaRepository.findByStack(stack)?.toDomain()
            ?: throw TechStackNotFoundException
}