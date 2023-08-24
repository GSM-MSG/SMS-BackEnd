package team.msg.sms.domain.project.spi

import team.msg.sms.domain.project.model.Project
import java.util.UUID

interface QueryProjectPort {
    fun queryAllProjectByStudentId(studentId: UUID) : List<Project>

    fun queryOneByProject(project: Project): Project?
}