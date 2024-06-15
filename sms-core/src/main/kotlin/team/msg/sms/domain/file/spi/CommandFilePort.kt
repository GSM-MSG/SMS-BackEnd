package team.msg.sms.domain.file.spi

import team.msg.sms.domain.file.model.File

interface CommandFilePort {
    fun saveAll(file: List<File>)
}