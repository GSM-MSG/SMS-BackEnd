package team.msg.sms.domain.student.spi

import team.msg.sms.domain.student.model.StudentLink

interface CommandStudentLinkPort {
    fun save(studentLink: StudentLink): StudentLink
}
