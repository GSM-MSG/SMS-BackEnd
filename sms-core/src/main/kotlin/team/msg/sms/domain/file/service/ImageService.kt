package team.msg.sms.domain.file.service

import team.msg.sms.common.annotation.Service

@Service
class ImageService(
    commandImageService: CommandImageService,
    getImageService: GetImageService
) : CommandImageService by commandImageService,
    GetImageService by getImageService