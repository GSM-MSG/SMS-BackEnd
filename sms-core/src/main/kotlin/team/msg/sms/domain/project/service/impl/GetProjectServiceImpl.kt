package team.msg.sms.domain.project.service.impl

import team.msg.sms.common.annotation.Service
import team.msg.sms.domain.project.model.Project
import team.msg.sms.domain.project.service.GetProjectService
import team.msg.sms.domain.project.spi.ProjectPort
import java.util.UUID

@Service
class GetProjectServiceImpl(
    private val projectPort: ProjectPort
) : GetProjectService {
    override fun getAllProjectByStudentId(studentid: UUID): List<Project> =
        projectPort.queryAllProjectByStudentId(studentId = studentid)
}