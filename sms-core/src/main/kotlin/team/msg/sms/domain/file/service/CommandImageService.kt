package team.msg.sms.domain.file.service

import team.msg.sms.domain.file.model.Image
import team.msg.sms.domain.project.model.Project

interface CommandImageService {
    fun saveAll(images: List<Image>)
    fun deleteAllByProjects(projects: List<Project>)
}