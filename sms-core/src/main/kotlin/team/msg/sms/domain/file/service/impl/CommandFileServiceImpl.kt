package team.msg.sms.domain.file.service.impl

import team.msg.sms.common.annotation.Service
import team.msg.sms.domain.file.model.File
import team.msg.sms.domain.file.service.CommandFileService
import team.msg.sms.domain.file.spi.FilePort

@Service
class CommandFileServiceImpl(
    private val filePort: FilePort
) : CommandFileService {
    override fun saveAll(file: List<File>) =
        filePort.saveAll(file)
}