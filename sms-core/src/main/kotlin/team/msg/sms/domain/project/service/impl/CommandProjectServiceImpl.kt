package team.msg.sms.domain.project.service.impl

import team.msg.sms.common.annotation.Service
import team.msg.sms.domain.project.model.Project
import team.msg.sms.domain.project.service.CommandProjectService
import team.msg.sms.domain.project.spi.ProjectPort
import team.msg.sms.domain.student.model.Student

@Service
class CommandProjectServiceImpl(
    private val projectPort: ProjectPort
) : CommandProjectService {
    override fun save(project: Project): Project =
        projectPort.save(project)

    override fun deleteAllByStudent(student: Student) =
        projectPort.deleteAllByStudent(student)
}