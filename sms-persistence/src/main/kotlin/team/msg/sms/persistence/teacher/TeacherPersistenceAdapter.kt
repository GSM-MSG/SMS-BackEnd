package team.msg.sms.persistence.teacher

import org.springframework.stereotype.Component
import team.msg.sms.domain.teacher.model.Teacher
import team.msg.sms.domain.teacher.spi.TeacherPort
import team.msg.sms.domain.user.model.User
import team.msg.sms.persistence.teacher.mapper.toDomain
import team.msg.sms.persistence.teacher.mapper.toEntity
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

    override fun findTeacherByUser(user: User): Teacher {
        return teacherJpaRepository.findByUser(user.toEntity()).toDomain()
    }

    override fun queryTeacherByUserId(userId: UUID): Teacher =
        teacherJpaRepository.findTeacherJpaEntityByUserId(userId).toDomain()

    override fun saveTeacher(teacher: Teacher, user: User) =
        teacherJpaRepository.save(teacher.toEntity(user.toEntity())).toDomain()

}