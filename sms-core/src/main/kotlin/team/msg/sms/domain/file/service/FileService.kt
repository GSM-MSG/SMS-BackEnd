package team.msg.sms.domain.file.service

import team.msg.sms.common.annotation.Service

@Service
class FileService(
    getFileService: GetFileService,
    commandFileService: CommandFileService
) : GetFileService by getFileService,
    CommandFileService by commandFileService