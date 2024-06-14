package team.msg.sms.domain.file.service

import team.msg.sms.domain.file.model.File

interface CommandFileService {
    fun saveAll(file: List<File>)
}