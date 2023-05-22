package team.msg.sms.domain.file.exception

import team.msg.sms.common.error.SmsException
import team.msg.sms.domain.file.exception.error.FileErrorCode

object ImageInvalidExtensionException : SmsException (
    FileErrorCode.IMAGE_INVALID_EXTENSION
)