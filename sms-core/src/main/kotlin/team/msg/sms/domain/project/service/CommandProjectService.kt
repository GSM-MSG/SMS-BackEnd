package team.msg.sms.domain.project.service

import team.msg.sms.domain.project.model.Project
import team.msg.sms.domain.student.model.Student

interface CommandProjectService {
    fun save(project: Project): Project
    fun deleteAllByStudent(student: Student)
    fun deleteByProject(project: Project, student: Student)
}