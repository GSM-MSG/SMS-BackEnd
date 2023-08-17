package team.msg.sms.domain.project.spi

import team.msg.sms.domain.project.model.Project
import team.msg.sms.domain.student.model.Student

interface CommandProjectPort {
    fun save(project: Project): Project
    fun deleteAllByStudent(student: Student)
}