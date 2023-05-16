package team.msg.sms.domain.file.exception

import team.msg.sms.common.error.SmsException
import team.msg.sms.domain.file.exception.error.FileErrorCode

object FileInvalidExtensionException : SmsException (
    FileErrorCode.FILE_INVALID_EXTENSION
)