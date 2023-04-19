package team.msg.sms.persistence.user

import org.springframework.stereotype.Component
import team.msg.sms.domain.user.model.User
import team.msg.sms.domain.user.spi.UserPort
import team.msg.sms.persistence.user.mapper.toDomain
import team.msg.sms.persistence.user.mapper.toEntity
import team.msg.sms.persistence.user.repository.UserJpaRepository

@Component
class UserPersistenceAdapter(
//    private val userMapper: UserMapper,
    private val userRepository: UserJpaRepository
) : UserPort {
    override fun saveUser(user: User): User =
        userRepository
            .save(user.toEntity())
            .toDomain()

    override fun queryUserByEmail(email: String): User? = 
            userRepository.findByEmail(email)?.toDomain()

    override fun existsUserByEmail(email: String): Boolean =
        userRepository.existsByEmail(email)
}