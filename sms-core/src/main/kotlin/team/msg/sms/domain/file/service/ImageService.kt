package team.msg.sms.domain.file.service

import team.msg.sms.common.annotation.Service

@Service
class ImageService(
    commandImageService: CommandImageService,
    getImageService: GetImageService,
    checkImageService: CheckImageService
) : CommandImageService by commandImageService,
    GetImageService by getImageService,
    CheckImageService by checkImageService