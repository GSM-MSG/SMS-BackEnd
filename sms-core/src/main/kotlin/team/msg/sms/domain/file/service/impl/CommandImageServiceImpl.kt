package team.msg.sms.domain.file.service.impl

import team.msg.sms.common.annotation.Service
import team.msg.sms.domain.file.model.Image
import team.msg.sms.domain.file.service.CommandImageService
import team.msg.sms.domain.file.spi.ImagePort
import team.msg.sms.domain.project.model.Project

@Service
class CommandImageServiceImp(
    private val imagePort: ImagePort
) : CommandImageService {
    override fun saveAll(images: List<Image>) =
        imagePort.saveAll(images)

    override fun deleteAllByProjects(projects: List<Project>) =
        imagePort.deleteAllByProjects(projects)
}