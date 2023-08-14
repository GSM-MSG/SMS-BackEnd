package team.msg.sms.domain.file.spi

import team.msg.sms.domain.file.model.Image
import team.msg.sms.domain.project.model.Project

interface CommandImagePort {
    fun saveAll(images: List<Image>)
    fun deleteAllByProjects(projects: List<Project>)
}