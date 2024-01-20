package team.msg.sms.persistence.teacher

import org.springframework.stereotype.Component
import team.msg.sms.domain.teacher.spi.TeacherPort
import team.msg.sms.domain.user.model.User
import team.msg.sms.persistence.teacher.repository.TeacherJpaRepository
import team.msg.sms.persistence.user.mapper.toEntity
import java.util.*

@Component
class TeacherPersistenceAdapter(
    private val teacherJpaRepository: TeacherJpaRepository
) : TeacherPort {
    override fun existsTeacherById(uuid: UUID) =
        teacherJpaRepository.existsById(uuid)

    override fun existsTeacherByUser(user: User) =
        teacherJpaRepository.existsByUser(user.toEntity())

}